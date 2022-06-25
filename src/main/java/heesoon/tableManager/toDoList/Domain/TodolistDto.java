package heesoon.tableManager.toDoList.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TodolistDto {
    private String title;
    private String plan;
    private String plan_date;
    private String status;
    private Long memberId;

    public TodolistDto toDto(Todolist todo) {
        return TodolistDto.builder().title(todo.getTitle())
                .plan(todo.getPlan())
                .status(todo.getStatus())
                .memberId(todo.getMemberId().getMemberId()).build();
    }

}
