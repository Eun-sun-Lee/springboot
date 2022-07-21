package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //jpa가 관리하는 entity임을 명시
public class Member { //id, 식별자 필요
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)//PK mapping
    //DB가 id를 자동으로 생성해줌 -> IDENTITY 전략
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
