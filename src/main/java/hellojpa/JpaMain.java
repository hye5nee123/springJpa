package hellojpa;

import jakarta.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        //loading 시점에 딱 하나만 만들어야 함.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //transaction 얻기
        EntityTransaction tx = em.getTransaction();
        //시작
        tx.begin();


        //엔티티 매니저는 쓰레드 공간에 공유X(사용하고 버려야 함)
        //JPA의 모든 데이터 변경은 트랜잭션 안에서 실행(중요)

        try{
//            //code
//            Member member = new Member();
//            //저장 완.
//
//            member.setId(10L);
//            member.setName("HelloB");
//                Member findMember = em.find(Member.class, 1);
//                findMember.setName("HelloJPA");
//                //코드를 저장해라.
//                em.persist(member);
                //정상적이면 commit
            //가져올 때는 Table이 대상이 아니라 객체를 대상으로 싹다 가져옴.
           List<Member> result = em.createQuery("SELECT m FROM Member as m", Member.class)
                   //paging 쉽게 가능
                   .setFirstResult(5)
                   .setMaxResults(10)
                   .getResultList();

           for ( Member member : result ) {
               System.out.printf("member.name = " + member.getName());
           }
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
