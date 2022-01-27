package syscon.hellospring.repository;

import syscon.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    //JPA를 쓰려면 EntityManager를 주입받아야한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //pk는 이런식으로 받으면 되는데 아래 name이나 all 같은 경우는 다름(jpql 쿼리 언어 사용)
        Member member = em.find(Member.class, id);   //find(조회할 타입, PK)
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        //하나만 찾을꺼니까 stream().findAny() 값 리턴
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
//        List<Member> result = em.createQuery("select m from Member  m", Member.class)
//                .getResultList();
//        return result;

        // result result 같을 때. 인라인으로 바꾸는 단축키 : ctrl + alt + n(아래 결과)
        //jpql 언어: 테이블을 대상이 아닌 엔티티를 대상으로 쿼리를 날림
        return em.createQuery("select m from Member  m", Member.class)
                .getResultList();
    }
}
