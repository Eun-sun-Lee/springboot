package hello.hellospring.controller;

public class MemberForm {
    private String name; //templates-members-createMemberForm.html의 name과 연결돼서 값 들어옴.
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
}
