package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    //method가 끝날때마다 동작함-> 순서가 상관 없어짐.
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member= new Member();
        member.setName("spring");

        repository.save(member);
        Member result=repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
        //Assertions.assertThat(member).isEqualTo(result);
        //Assertions.assertEquals(member,result); //기댓값 비교값
        //result와 member 객체가 같은지 확인 후 true/false로 리턴
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result=repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1= new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2= new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result=repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }


}
