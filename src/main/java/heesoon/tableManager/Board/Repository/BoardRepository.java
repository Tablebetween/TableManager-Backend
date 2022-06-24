package heesoon.tableManager.Board.Repository;

import heesoon.tableManager.Board.Domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
