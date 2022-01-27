package syscon.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import syscon.hellospring.aop.TimeTraceAop;
import syscon.hellospring.repository.MemberRepository;
import syscon.hellospring.service.MemberService;

//@Configuration 해놓으면 스프링 뜰 떄 configuration 읽고 그 안에 @Bin들 읽어서 자동 등록
@Configuration
public class SpringConfig {
    /*  ..return new JdbcTemplateMemberRepository(dataSource); JdbcTemplate 쓸 때 사용
        private final DataSource dataSource;

        public SpringConfig(DataSource dataSource){
            this.dataSource = dataSource;
        }
        */

  /*
     ..return new JpaMemberRepository(em);  JPA 쓸 때 사용
    private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

   */

    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
       // return new MemberService(memberRepository()); //멤버서비스는 멤버리포지토리 사용하도록 호출
        return new MemberService(memberRepository);         //스프링데이터 JPA쓸 때
    }
    
//    @Bean
//    public TimeTraceAop TimeTraceAop(){
    //TimeTraceAop 가서 @Component 스캔 쓰거나 여기서 @Bean 등록해서 쓰거나
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memberRepository(){
////        return new MemoryMemberRepository();
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(em);
//    }


}
