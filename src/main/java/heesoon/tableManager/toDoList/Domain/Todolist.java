package heesoon.tableManager.toDoList.Domain;


import heesoon.tableManager.Member.Domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Todolist")
@NoArgsConstructor

public class Todolist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "todo_id")
    private Long todoid;
    private String title;
    private String plan;
    private String status;
    private String plan_date;
    private String inp_dthms;
    private boolean use_yn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member memberId;

    @Builder
    public Todolist(String title, String plan, String status, String plan_date, String inp_dthms, boolean use_yn, Member memberId)
    {
        this.title = title;
        this.plan = plan;
        this.status = status;
        this.plan_date = plan_date;
        this.inp_dthms = inp_dthms;
        this.use_yn = use_yn;
        this.memberId = memberId;
    }
    public Todolist updateTodoList(String title, String plan, String status)
    {
        this.title = title;
        this.plan = plan;
        this.status = status;

        return this;
    }
}
