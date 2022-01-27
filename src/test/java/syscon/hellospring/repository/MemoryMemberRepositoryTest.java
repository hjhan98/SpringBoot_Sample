package syscon.hellospring.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import syscon.hellospring.domain.Member;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("hajeong");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
//        System.out.println("result =" + (result == member));
        Assertions.assertEquals(member, result); //(예상값, 실제 값) true면 아무것도
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("hajeong");
        repository.save(member1);

        Member member2 = new Member();      //shift + f6 (rename)
        member2.setName("hajeong22");
        repository.save(member2);

        Member result = repository.findByName("hajeong").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("hajeong");
        repository.save(member1);

        Member member2= new Member();
        member2.setName("hajeong22");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}
