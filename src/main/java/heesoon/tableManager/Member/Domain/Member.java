package heesoon.tableManager.Member.Domain;

import heesoon.tableManager.Alarm.Domain.Alarm;
import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Domain.Timeentity;
import heesoon.tableManager.Comment.Domain.Comment;
import heesoon.tableManager.Follow.Domain.Follow;
import heesoon.tableManager.LikeMark.Domain.LikeMark;
import heesoon.tableManager.toDoList.Domain.Todolist;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Member")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private String pfUrl;
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

    @OneToMany(mappedBy ="memberId")
    @Cascade(value = { CascadeType.ALL })
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy ="following")
    @Cascade(value = { CascadeType.ALL })
    private List<Follow> followingList = new ArrayList<>();

    @OneToMany(mappedBy ="follower")
    @Cascade(value = { CascadeType.ALL })
    private List<Follow> followerList = new ArrayList<>();

    @OneToMany(mappedBy ="sendMemberId")
    @Cascade(value = { CascadeType.ALL })
    private List<Alarm> sendAlarmList = new ArrayList<>();

    @OneToMany(mappedBy ="receiveMemberId")
    @Cascade(value = { CascadeType.ALL })
    private List<Alarm> receiveAlarmList = new ArrayList<>();

    @OneToMany(mappedBy = "memberId")
    @Cascade(value = {CascadeType.ALL})
    private List<LikeMark> likeMarkList = new ArrayList<>();



    //JsonIgnore 제거

    @Builder
    public Member(String username, String password, String name, String birth, String email, String intro, String pfUrl,String sex,String nickname, MemberRole role)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.intro = intro;
        this.pfUrl = pfUrl;
        this.sex = sex;
        this.nickname = nickname;
        this.role = role;
    }
}
