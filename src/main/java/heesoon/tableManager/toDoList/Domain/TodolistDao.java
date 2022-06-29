package heesoon.tableManager.toDoList.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public TodolistDao toDto(Todolist todo) {
        return TodolistDao.builder()
                .todoid(todo.getTodoid())
                .title(todo.getTitle())
                .plan(todo.getPlan())
                .plan_date(todo.getPlan_date())
                .status(todo.getStatus()).build();
    }


}
