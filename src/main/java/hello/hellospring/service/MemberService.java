package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //회원가입 (단, 같은 이름의 회원은 안 됨)
    public Long join(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { //optional로 감싸면 
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });//만약 값이 있다면
    }
}
