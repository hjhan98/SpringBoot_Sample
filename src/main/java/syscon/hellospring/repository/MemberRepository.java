package syscon.hellospring.repository;

import org.springframework.stereotype.Repository;
import syscon.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {
    Member save(Member member); //회원 저장하면 저장된 회원 반환됨(domain의 member)
    Optional<Member> findById(Long id); //Optional : Java8 기능 중 하난데 값이 Null일때 Null 반환 안하고 이걸로 감싸서 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
