package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    //test 코드는 build 파일에 포함 X
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository=new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); //같은 memberRepository 사용하게 만들어줌.
        //memberservice 입장에서 외부의 memberRepository를 넣어줌 -> DI
    }

    @AfterEach
    //method가 끝날때마다 동작함-> 순서가 상관 없어짐.
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given 주어졌을 때
        Member member = new Member();
        member.setName("hello");
        //when 실행하면
        Long saveId=memberService.join(member);
        //then OO가 나와야함 (검증부)
        Member findMember =memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1=new Member();
        member1.setName("spring");
        Member member2=new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e =assertThrows(IllegalStateException.class, () ->memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //예외가 터져야함

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}