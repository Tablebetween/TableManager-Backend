package heesoon.tableManager.toDoList.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TodolistDao {
    private Long todoid;
    private String title;
    private String plan;
    private String plan_date;
    private String status;
    private boolean use_yn;
    private LocalDateTime updatedAt;

    public TodolistDao(Todolist todolist)
    {
        this.todoid = todolist.getTodoid();
        this.title = todolist.getTitle();
        this.plan = todolist.getPlan();
        this.plan_date = todolist.getPlan_date();
        this.status = todolist.getStatus();
        this.use_yn = todolist.isUse_yn();
        this.updatedAt = todolist.getUpdatedAt();
    }

    public TodolistDao toDto(Todolist todo) {
        return TodolistDao.builder()
                .todoid(todo.getTodoid())
                .title(todo.getTitle())
                .plan(todo.getPlan())
                .plan_date(todo.getPlan_date())
                .status(todo.getStatus()).build();
    }


}
