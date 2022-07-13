package hello.hellospring.domain;

public class Member { //id, 식별자 필요
    private Long id; //구분 위함
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
