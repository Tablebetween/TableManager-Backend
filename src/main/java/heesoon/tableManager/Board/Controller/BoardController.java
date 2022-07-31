package heesoon.tableManager.Board.Controller;

import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Domain.BoardDto;
import heesoon.tableManager.Board.Domain.BoardDao;
import heesoon.tableManager.Board.Service.BoardService;
import heesoon.tableManager.Security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        Board info = boardService.makeBoard(boardDto,file);
        BoardDto boardInfo = new BoardDto().toDto(info);
        return ResponseEntity.ok(boardInfo);
    }
    @GetMapping
    ResponseEntity<?> loadMyBoard(@AuthenticationPrincipal PrincipalDetails principalDetails)
    {
        List<BoardDao> info = boardService.loadMyBoardById(principalDetails.getMember().getMemberId());
        return ResponseEntity.ok(info);
    }
//    @GetMapping("/{boardId}")
//    ResponseEntity<?> loadBoardById(@PathVariable Long id)
//    {
//        BoardDao boardDao = boardService.loadboardbyid(id);
//        return ResponseEntity.ok(boardDao);
//    }
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteboard(@PathVariable Long id)
    {
        boardService.boardDelete(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PutMapping("/{id}")
    ResponseEntity<?> updateboard(@PathVariable Long id, @RequestBody BoardDto boardDto)
    {
        boardService.boardUpdate(id,boardDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
