package heesoon.tableManager.toDoList.Service;


import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Repository.MemberRepository;
import heesoon.tableManager.toDoList.Domain.Todolist;
import heesoon.tableManager.toDoList.Domain.TodolistDto;
import heesoon.tableManager.toDoList.Repository.TodolistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TodolistServiceImpl implements TodolistService {
    private final MemberRepository memberRepository;
    private final TodolistRepository todolistRepository;
    @Override
    public Todolist makeTodolist(TodolistDto todolistDto) {
        Member cmember = memberRepository.findById(todolistDto.getMemberId()).orElse(null);
        LocalDate now = LocalDate.now();
        Todolist todolist = Todolist.builder().title(todolistDto.getTitle())
                .plan(todolistDto.getPlan())
                .inp_dthms(now.toString())
                .plan_date(todolistDto.getPlan_date())
                .status(todolistDto.getStatus())
                .memberId(cmember).build();
        todolistRepository.save(todolist);
        return todolist;

    }

    @Override
    public List<Todolist> getTodolist(Long member_id) {
        Member cmember = memberRepository.findById(member_id).orElse(null);
        List<Todolist> todolists = todolistRepository.findAllBymemberId(cmember);
        return todolists;
    }
}
