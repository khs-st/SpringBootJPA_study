package helloJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        //persistence.xml의 name값 넣기
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

       EntityManager entityManager = emf.createEntityManager();
       //code
       entityManager.close();

       emf.close();
    }
}
