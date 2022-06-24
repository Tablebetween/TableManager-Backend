package heesoon.tableManager.Member.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import heesoon.tableManager.Board.Domain.Board;
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
    private Long member_id;
    private String name;
    private String birth;
    private String email;
    private String intro;
    private String inp_dthms;
    private String mdf_dthms;
    private String pf_url;
    private String sex;
    private String nick_name;

    @OneToMany(mappedBy ="member_id")
    @Cascade(value = { CascadeType.ALL })
    @JsonIgnore
    private List<Board> Board = new ArrayList<Board>();

    @Builder
    public Member(String name, String birth, String email, String intro, String inp_dthms, String mdf_dthms, String pf_url,String sex,String nick_name)
    {
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.intro = intro;
        this.inp_dthms = inp_dthms;
        this.mdf_dthms = mdf_dthms;
        this.pf_url = pf_url;
        this.sex = sex;
        this.nick_name = nick_name;
    }
}
