package syscon.hellospring.domain;

import javax.persistence.*;

@Entity
@Table(name= "tb_member")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@ID -> 해당 테이블의 PK필드를 나타낸다.
    //@GeneratedValue ->PK 생성규칙(Identity 옵션- AutoIncreasement)
    private Long id;

    @Column(name="name")
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
