package heesoon.tableManager.Member.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Comment.Domain.Comment;
import heesoon.tableManager.Follow.Domain.Follow;
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
public class Member {
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
    private String inp_dthms;
    private String mdf_dthms;
    private String pf_url;
    private String sex;
    private String nick_name;

    @Enumerated(value = EnumType.STRING)
    private MemberRole role;

    @OneToMany(mappedBy ="memberId")
    @Cascade(value = { CascadeType.ALL })
    private List<Board> Board = new ArrayList<Board>();

    @OneToMany(mappedBy ="memberId")
    @Cascade(value = { CascadeType.ALL })
    private List<Todolist> todolists = new ArrayList<>();

    @OneToMany(mappedBy ="memberId")
    @Cascade(value = { CascadeType.ALL })
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy ="following")
    @Cascade(value = { CascadeType.ALL })
    private List<Follow> followingList = new ArrayList<>();

    @OneToMany(mappedBy ="follower")
    @Cascade(value = { CascadeType.ALL })
    private List<Follow> followerList = new ArrayList<>();

    //JsonIgnore 제거

    @Builder
    public Member(String username, String password, String name, String birth, String email, String intro, String inp_dthms, String mdf_dthms, String pf_url,String sex,String nick_name, MemberRole role)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.intro = intro;
        this.inp_dthms = inp_dthms;
        this.mdf_dthms = mdf_dthms;
        this.pf_url = pf_url;
        this.sex = sex;
        this.nick_name = nick_name;
        this.role = role;
    }
}
