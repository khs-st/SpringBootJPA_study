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

            //영속
            //Member member1 = new Member(150L,"A");
            //Member member2 = new Member(160L,"B");
            //em.persist(member1);
            //em.persist(member2);
            //System.out.println("=================");
            
            //엔티티 수정 - 변경 감지
            //Member member = em.find(Member.class,150L);
            //데이터 변경 후 persist 호출할 필요 없다.
            //member.setName("Modify Name1");

            //영속성 컨텍스트를 플러시 하는 방법
            Member member = new Member(300L,"member300");
            em.persist(member);

            // 쓰기 지연 SQL 저장소의 쿼리를 데이터베이스에 반영(등록, 수정, 삭제 쿼리)
            //트랜잭션 commit과 상관없이 즉시(쿼리가 DB에 반영) 일어난다.
            em.flush();

            System.out.println("==================");

            //커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.
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
