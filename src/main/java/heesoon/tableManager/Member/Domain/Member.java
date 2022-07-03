package heesoon.tableManager.Member.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Domain.Timeentity;
import heesoon.tableManager.toDoList.Domain.Todolist;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Member")
@Setter
@NoArgsConstructor
public class Member extends Timeentity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long memberId;
    private String username; //로그인 아이디
    private String name;     //회원 이름
    private String password;
    private String birth;
    private String email;
    private String intro;
    private String pf_url;
    private String sex;
    private String nickname;

    @Enumerated(value = EnumType.STRING)
    private MemberRole role;

    @OneToMany(mappedBy ="memberId")
    @Cascade(value = { CascadeType.ALL })
    private List<Board> Board = new ArrayList<Board>();

    @OneToMany(mappedBy ="memberId")
    @Cascade(value = { CascadeType.ALL })
    private List<Todolist> todolists = new ArrayList<>();

    //JsonIgnore 제거

    @Builder
    public Member(String username, String password, String name, String birth, String email, String intro, String pf_url,String sex,String nickname, MemberRole role)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.intro = intro;
        this.pf_url = pf_url;
        this.sex = sex;
        this.nickname = nickname;
        this.role = role;
    }
}
