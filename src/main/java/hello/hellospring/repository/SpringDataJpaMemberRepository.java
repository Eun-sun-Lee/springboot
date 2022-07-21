package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//interface에서 interface import, 인터페이스는 다중 상속 가능
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { //member id는 long type
    //JPQL select m from Member m where m.name=?
    //인터페이스 이름만으로도 개발 끝
    @Override
    Optional<Member> findByName(String name);


} //구현체를 만들어서 자동으로 스프링 빈에 등록해줌.
