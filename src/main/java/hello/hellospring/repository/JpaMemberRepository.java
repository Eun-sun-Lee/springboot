package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em; //springboot가 자동으로 entityManager 생성해줌.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //영구저장하다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member=em.find(Member.class, id); //find method -> 조회할 타입, 조회할 식별자(PK)
        return Optional.ofNullable(member); //nullable하므로
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result=em.createQuery("select m from Member as m where m.name= :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
        //저장, 조회, update는 자동으로 해줌.
        //PK 기반이 아닌 나머지들은 JPQL 작성 필요
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //JPQL 객체지향 언어 사용해야함.
            .getResultList(); //Jpql -> table 대상이 아닌 entity를 대상으로 query를 날림.
    }
}
