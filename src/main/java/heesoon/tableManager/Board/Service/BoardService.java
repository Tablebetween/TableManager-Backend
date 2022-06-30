package heesoon.tableManager.Board.Service;

import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Domain.BoardDto;
import heesoon.tableManager.Board.Domain.BoardDao;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public interface BoardService {
    Board makeboard(BoardDto boardDto, MultipartFile file) throws IOException, ParseException;
    List<BoardDao> loadboardbyid(Long id);
    void boarddelete(Long id);
    Board boardupdate(Long id, BoardDto boardDto);
}
