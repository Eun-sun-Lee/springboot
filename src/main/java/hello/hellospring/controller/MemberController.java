package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    private MemberService memberService;
    @Autowired //*****1. 생성자 주입
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    } //스프링이 컨테이너에 있는 memberService를 가져다가 연결 시켜줌.


    //*****2. 필드 주입 -> @Autowired private MemberService memberservice;
    //******3. setter ->
    // private MemeberService memberService;
    // @Autowired
       // public void setMemberService(MemberService MemberService) {
       //          this.memberService=memberService;}
       //중간에 잘못 바꾸면 문제가 생김.

    //여러개의 instance를 생성할 필요 없이 하나의 instance만 생성해서 공유하고 쓰면 됨.
    //스프링 컨테이너에 등록해서 쓰면 됨.
    @GetMapping(value="/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
}
