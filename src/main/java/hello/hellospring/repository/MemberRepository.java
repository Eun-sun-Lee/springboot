package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    //저장된 회원 반환
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //id나 name으로 회원 찾을 수 있음
    //Optional -> null 처리 방법
    List<Member> findAll();
}
