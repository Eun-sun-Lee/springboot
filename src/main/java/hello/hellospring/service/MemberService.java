package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional //Jpql을 사용하여 데이터 저장, 변경할 때 필요
//스프링 컨테이너에 memberService를 등록해줌.
public class MemberService {
    private MemberRepository memberRepository;
    //memberRepository가 필요하다고 인지함 -> 스프링 컨테이너에 있는 memberRepository를 넣어준다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository=memberRepository;
    } //DI (생성자 주입)

    //회원가입 (단, 같은 이름의 회원은 안 됨)
    public Long join(Member member) {
        validateDuplicateMember(member);//중복 회원 검증
        //+ Optional의 orElseGet -> 값이 있다면 꺼내고 없다면 default 값 설정/method 실행 가능
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //optional로 감싸면 optional 안에 객체 존재 -> optional의 다양한 method 사용 가능
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(long memberId) {
        return memberRepository.findById(memberId);
    }

}
