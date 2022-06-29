package heesoon.tableManager.Board.Repository;

import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.toDoList.Domain.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findBymemberId(Member member);
}
