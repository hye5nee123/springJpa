package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {
        //loading 시점에 딱 하나만 만들어야 함.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //transaction 얻기
        EntityTransaction tx = em.getTransaction();
        //시작
        tx.begin();

        try{
//            //code
//            Member member = new Member();
//            //저장 완.
//
//            member.setId(10L);
//            member.setName("HelloB");
                Member findMember = em.find(Member.class, 1);
                findMember.setName("HelloJPA");
//                //코드를 저장해라.
//                em.persist(member);
                //정상적이면 commit
                tx.commit();

        } catch (Exception e){
            //만약에 error 나면 rollback
            tx.rollback();
        } finally {
            //내부적으로 db를 물고 동작하기에 무조건 닫아 줘야 함.
            em.close();
        }
        //마지막으로 factory까지 닫아줘라.
        emf.close();

  }

}
