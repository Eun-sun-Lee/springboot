package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//구현체
public class MemoryMemberRepository implements MemberRepository{
    //key값 Long(회원 id이므로), value값 member
    private static Map<Long, Member> store=new HashMap<>();//동시성 문제 때문에 공유문제일때 hashmap 사용
    private static long sequence=0L;
    //key값 생성 해줌

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //null이여도 감쌀 수 있음.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
                //값을 찾으면 처음꺼 반환함. 없으면 null 리턴
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
