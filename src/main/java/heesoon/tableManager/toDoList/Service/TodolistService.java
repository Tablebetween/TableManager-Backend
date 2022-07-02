package heesoon.tableManager.toDoList.Service;

import heesoon.tableManager.toDoList.Domain.Todolist;
import heesoon.tableManager.toDoList.Domain.TodolistDao;
import heesoon.tableManager.toDoList.Domain.TodolistDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TodolistService {

    Todolist makeTodolist(TodolistDto todolistDto);
    List<TodolistDao> getTodolist(Long member_id);
    void deleteTodoList(Long todoId);
    void updateTodoList(Long id, TodolistDto todolistDto);
}
