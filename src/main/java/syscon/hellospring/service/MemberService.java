package syscon.hellospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syscon.hellospring.domain.Member;
import syscon.hellospring.repository.MemberRepository;
import syscon.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //외부에서 파라미터로 넣을 수 있도록 레파지토리 Construter 만들기
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //자동 반환값 단축키 : ctrl + alt + v
    //ctrl + alt + M(코드 함수로 뺴기)

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);    //중복 회원 검증
        memberRepository.save(member);  //통과하면 저장
        Optional<Member> result = memberRepository.findByName(member.getName());
        //Optional 쓰면 아래와 같이 if문 안써도 Optional을 통해 여러메서드(ex. ifPresent) 사용 가능 . null 가능성 있으면 Optional로 한번 감싸기
        result.ifPresent(m ->{  //member에 값이 있으면
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        /**/
        //같은 이름이 있는 중복 회원X
        memberRepository.findByName(member.getName())       //findName 조회 해서
                .ifPresent(m -> {                             //결과 있으면 바로 Exception 때리기
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberID) {
        return memberRepository.findById(memberID);
    }
}
