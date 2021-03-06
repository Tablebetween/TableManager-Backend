package heesoon.tableManager.toDoList.Controller;


import heesoon.tableManager.toDoList.Domain.Todolist;
import heesoon.tableManager.toDoList.Domain.TodolistDao;
import heesoon.tableManager.toDoList.Domain.TodolistDto;
import heesoon.tableManager.toDoList.Service.TodolistService;
import lombok.AllArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/todo")
public class TodolistController {
    private final TodolistService todolistService;

    @PostMapping
    public ResponseEntity<?> makeTodolist(@RequestBody TodolistDto todolistDto) throws IOException, ParseException
    {
        Todolist todolist = todolistService.makeTodolist(todolistDto);
        TodolistDao todoinfo = new TodolistDao().toDto(todolist);
        return ResponseEntity.ok(todoinfo);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getTodolist(@PathVariable Long id)
    {
        List<TodolistDao> todolist = todolistService.getTodolist(id);
        return ResponseEntity.ok(todolist);
    }
    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodoList(@PathVariable Long todoId)
    {
        todolistService.deleteTodoList(todoId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodoList(@PathVariable Long id, @RequestBody TodolistDto todolistDto)
    {
        todolistService.updateTodoList(id,todolistDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
