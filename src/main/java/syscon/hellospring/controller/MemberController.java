package syscon.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import syscon.hellospring.domain.Member;
import syscon.hellospring.service.MemberService;

import java.sql.SQLOutput;
import java.util.List;

@Controller
public class MemberController {


    private final MemberService memberService;
    //new로 새로운 MemberService 객체 생성 안하고 스프링컨테이너에 하나만 등록(생성자 하나 호출해서 연결 - Alt+Insert > Constructor)

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        //get 방식은 url에 저거 ""쳐서 들어오면 Get방식이라고 해서 그냥 아래 return값 호출. 주로 조회기능으로 사용
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        //post 방식은 form 태그에 데이터 넣어서 전달할 떄 주로 사용.(데이터 등록)
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " +member.getName());

        memberService.join(member);
        return "redirect:/";            //ridirect의 오른쪽 주소로 이동(:/ -> home으로 이동- 해당 URL에 속하는 컨트롤러의 함수 한번 더 호출)
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
