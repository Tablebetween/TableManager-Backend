package heesoon.tableManager.Board.Controller;

import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Domain.BoardDto;
import heesoon.tableManager.Board.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.json.simple.parser.ParseException;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    ResponseEntity<?> makeboard(@RequestPart(value = "BoardInfo")  BoardDto boardDto,
                                  @RequestPart(value = "image", required = false) MultipartFile file) throws IOException, ParseException {
        Board info = boardService.makeboard(boardDto,file);
        BoardDto boardInfo = new BoardDto().toDto(info);
        return ResponseEntity.ok(boardInfo);
    }
}
