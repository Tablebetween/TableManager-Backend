package heesoon.tableManager.Board.Domain;

import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.toDoList.Domain.Todolist;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Board")
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends Timeentity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long boardId;
    private String img_url;             //이미지 주소
    private String content;             //내용
    private boolean use_yn;             //사용확인

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member memberId;           //사용자 ID

    @Builder
    public Board(String img_url,String content,boolean use_yn,Member memberId)
    {
        this.img_url = img_url;
        this.content = content;
        this.use_yn = use_yn;
        this.memberId = memberId;
    }
    public Board updateBoard(String content)
    {
        this.content = content;
        return this;
    }

}
