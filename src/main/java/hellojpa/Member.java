package hellojpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//JPA를 사용하는 애구나!
@Entity

public class Member {
    //PRIMARY KEY
    @Id
    private Long id;
    private String name;
    //DB에 있는 것들

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
