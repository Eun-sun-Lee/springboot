package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member=new Member(); //입력 ? / 새로운 객체를 만들어야 할 땐 무조건 POST 방식
        member.setName(form.getName()); //member를 만듦.
        //System.out.println("member= " + member.getName());
        memberService.join(member); //member를 넘김.
        return "redirect:/"; //홈화면으로 보냄.
    }
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
