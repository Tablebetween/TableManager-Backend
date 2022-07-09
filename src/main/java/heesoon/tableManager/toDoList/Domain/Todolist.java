package heesoon.tableManager.toDoList.Domain;


import heesoon.tableManager.Board.Domain.Timeentity;
import heesoon.tableManager.Member.Domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Todolist")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter @Getter
public class Todolist extends Timeentity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "todo_id")
    private Long todoid;
    private String title;
    private String plan;
    private String status;
    private String plan_date;
    private boolean use_yn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member memberId;

    @Builder
    public Todolist(String title, String plan, String status, String plan_date, boolean use_yn, Member memberId)
    {
        this.title = title;
        this.plan = plan;
        this.status = status;
        this.plan_date = plan_date;
        this.use_yn = use_yn;
        this.memberId = memberId;
    }
    public Todolist updateTodoList(String title, String plan, String status, String plan_date)
    {
        this.title = title;
        this.plan = plan;
        this.status = status;
        this.plan_date = plan_date;

        return this;
    }
}
