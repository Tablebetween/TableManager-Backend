package heesoon.tableManager.toDoList.Service;


import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Repository.MemberRepository;
import heesoon.tableManager.toDoList.Domain.Todolist;
import heesoon.tableManager.toDoList.Domain.TodolistDao;
import heesoon.tableManager.toDoList.Domain.TodolistDto;
import heesoon.tableManager.toDoList.Repository.TodolistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class TodolistServiceImpl implements TodolistService {
    private final MemberRepository memberRepository;
    private final TodolistRepository todolistRepository;
    @Override
    public Todolist makeTodolist(TodolistDto todolistDto) {
        Member cmember = memberRepository.findById(todolistDto.getMemberId()).orElse(null);
        LocalDate now = LocalDate.now();
        Todolist todolist = Todolist.builder().title(todolistDto.getTitle())
                .plan(todolistDto.getPlan())
                .plan_date(todolistDto.getPlan_date())
                .status(todolistDto.getStatus())
                .memberId(cmember).build();
        todolistRepository.save(todolist);
        return todolist;

    }

    @Override
    public List<TodolistDao> getTodolist(Long member_id) {
        Member cmember = memberRepository.findById(member_id).orElse(null);
        List<Todolist> todolists = todolistRepository.findAllBymemberId(cmember);
        List<TodolistDao> todolistdaos = todolists.stream()
                .map(o -> new TodolistDao(o))
                .filter(use -> use.isUse_yn() == false)
                .sorted(Comparator.comparing(TodolistDao::getUpdatedAt).reversed())
                .collect(Collectors.toList());
//        List<TodolistDao> todolistdaos = new ArrayList<>();
//        for(int i=0;i<todolists.size();i++) {
//            if (todolists.get(i).isUse_yn() == true) {
//                continue;
//            } else {
//                TodolistDao Todoinfo = new TodolistDao().toDto(todolists.get(i));
//                todolistdaos.add(Todoinfo);
//            }
//        }
        return todolistdaos;
    }

    @Override
    public void deleteTodoList(Long todoId) {
        Todolist todolist = todolistRepository.findById(todoId).orElse(null);
        todolist.setUse_yn(true);
    }

    @Override
    public void updateTodoList(Long id, TodolistDto todolistDto) {
        todolistRepository.findById(id).map(entity -> entity.updateTodoList(
                    todolistDto.getTitle(),todolistDto.getPlan(),todolistDto.getStatus(),todolistDto.getPlan_date())).orElse(null);
    }
}
