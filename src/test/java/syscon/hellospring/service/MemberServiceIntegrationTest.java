package syscon.hellospring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import syscon.hellospring.domain.Member;
import syscon.hellospring.repository.MemberRepository;
import syscon.hellospring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
//MemberService 클래스에서 ctrl + shift + T 하면 자동 테스트케이스 껍데기 만들어줌

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    @Commit
    public void 회원가입() throws Exception{
        //given 뭔가가 주어졌을때
        Member member = new Member();
        member.setName("hajeong3");

        //when  뭐를 실행했는데
        Long saveId = memberService.join(member);

        //then  결과가 이게 나와야댐
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("hajeong");

        Member member2 = new Member();  // rename : shift + f6
        member2.setName("hajeong");

        //when
        memberService.join(member1);
        //아래 주석처리된 try-catch문과 기능 같음
//        assertThrows(IllegalStateException.class, ()->memberService.join(member2)); // join에 member2를 넣으면 Illegal Exception이 나와야함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// join에 member2를 넣으면 Illegal Exception이 나와야함
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}