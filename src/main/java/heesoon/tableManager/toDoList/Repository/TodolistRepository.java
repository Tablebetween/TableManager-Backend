package heesoon.tableManager.toDoList.Repository;


import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.toDoList.Domain.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodolistRepository extends JpaRepository<Todolist,Long> {
    List<Todolist> findAllBymemberId(Member member);
}
