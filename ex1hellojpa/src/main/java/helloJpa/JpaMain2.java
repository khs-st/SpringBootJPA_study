package helloJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**\
 * 엔티티 등록 - 트랜잭션을 지원하는 쓰기 지연
 * 엔티티 수정 - 변경 감지
 * 엔티티 삭제
 * 플러시 - 영속성 컨텍스트의 변경내용을 데이터베이스에 반영
 * 준영속 상태
 * 위 내용에 대한 실습이 있는 클래스이다.
 */

public class JpaMain2 {
    public static void main(String[] args) {

       EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
       

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Member member1 = new Member();
            member1.setUsername("D");
            Member member2 = new Member();
            member2.setUsername("E");
            Member member3 = new Member();
            member3.setUsername("F");

            System.out.println("===========================");
            //IDENTITY 전략은 em.persist() 시점에 즉시 INSERT SQL 실행하고 DB에서 식별자를 조회
            //시퀀스 전략은 이떄 시퀀스 값 불러온다.
            
            //em.persist()를 주석 처리 하면 시퀀스를 두번 호출하는걸 확인 할 수 있따.
            //DB_SEQ = 1 |  1
            //DB_SEQ = 51 |  2
            //DB_SEQ = 51 |  3

            em.persist(member1); //1, 51
            em.persist(member2); // DB 실행하지않고 메모리에서 호출
            em.persist(member3); // DB 실행하지않고 메모리에서 호출

            System.out.println("member1 id = "+member1.getId());
            System.out.println("member2 id = "+member2.getId());
            System.out.println("member3 id = "+member3.getId());
            System.out.println("===========================");
            
            //시퀀스 전략은 이떄 실질적 insert 쿼리를 실행한다.
            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            //자원을 다사용하면 닫는다. 데이터베이스 커넥션 내부적으로 반환
            em.close();
        }
       //웹어플리케이션일 경우 WAS가 내려갈 때 엔티티 매니저 팩토리 닫아줘야 커넥션 풀링 등 내부적으로 리소스 릴리즈 되기 때문에.
       emf.close();
    }
}
