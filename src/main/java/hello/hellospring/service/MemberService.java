package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository=memberRepository;
    }

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
