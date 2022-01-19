package helloJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //persistence.xml의 name값 넣기
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
       
       //엔티티 매니저 통해서 작업
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{   
            //조회
            //Member findMember = em.find(Member.class,1L);

            //수정
            //findMember.setName("HelloJPA");

            //조건 조회-예를 들면 나이가 18살 이상인 회원 모두 조회하고 싶다면? -> JPQL 사용해야한다.
            //전체 회원 검색
            List<Member> fineMemberList = em.createQuery("select m from Member as m",Member.class)
                    .getResultList();

            for (Member member : fineMemberList){
                System.out.println("member.name = " + member.getName());
            }

            //전체 회원 검색 페이징
            List<Member> fineMemberListPaging = em.createQuery("select m from Member as m",Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : fineMemberListPaging){
                System.out.println("member.name = " + member.getName());
            }
            
            //바꾼 후 반영
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
