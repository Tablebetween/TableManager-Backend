package heesoon.tableManager.Board.Controller;

import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Domain.BoardDto;
import heesoon.tableManager.Board.Domain.OutBoardDto;
import heesoon.tableManager.Board.Service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    ResponseEntity<?> makeboard(@RequestPart(value = "BoardInfo")  BoardDto boardDto,
                                  @RequestPart(value = "image") MultipartFile file) throws IOException, ParseException {
        log.info("boardDto",boardDto);
        Board info = boardService.makeboard(boardDto,file);
        BoardDto boardInfo = new BoardDto().toDto(info);
        return ResponseEntity.ok(boardInfo);
    }
    @GetMapping("/{id}")
    ResponseEntity<?> loadboardbyid(@PathVariable Long id)
    {
        List<OutBoardDto> info = boardService.loadboardbyid(id);
        return ResponseEntity.ok(info);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteboard(@PathVariable Long id)
    {
        boardService.boarddelete(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
