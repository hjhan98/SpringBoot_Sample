package syscon.hellospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syscon.hellospring.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    //JPQL select m from Member m where m.name = ?      //인터페이스 이름만으로도 개발 끝남
    @Override
    Optional<Member> findByName(String name);
}
