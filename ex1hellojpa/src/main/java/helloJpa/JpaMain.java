package helloJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        //persistence.xml의 name값 넣기
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

       EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{   
            //조회
            Member findMember = em.find(Member.class,1L);
            //수정
            findMember.setName("HelloJPA");
            

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            //code
            em.close();
        }
       emf.close();
    }
}
