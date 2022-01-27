package syscon.hellospring.repository;

import org.springframework.stereotype.Repository;
import syscon.hellospring.domain.Member;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements  MemberRepository{
    //implement하고 alt+enter해서 부모 기능들 상속받기(override)

    private static Map<Long, Member> store = new HashMap<>();
    private static  long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);   //id값 셋팅
        store.put(member.getId(), member);  //store에 저장
        return member;  //반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //Optional로 감싸서 넣기
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                    .filter(member -> member.getName().equals(name))
                    .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
