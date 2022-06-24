package heesoon.tableManager.Board.Domain;

import heesoon.tableManager.Member.Domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name="Board")
@Setter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Integer board_id;
    private String img_url;             //이미지 주소
    private String content;             //내용
    private String inp_dthms;           //작성일자
    private String mdf_dthms;           //수정일자
    private boolean use_yn;             //사용확인

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member_id;           //사용자 ID

    @Builder
    public Board(String img_url,String content,String inp_dthms,String mdf_dthms,boolean use_yn,Member member_id)
    {
        this.img_url = img_url;
        this.content = content;
        this.inp_dthms = inp_dthms;
        this.mdf_dthms = mdf_dthms;
        this.use_yn = use_yn;
        this.member_id = member_id;
    }

}
