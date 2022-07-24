package heesoon.tableManager.LikeMark.Controller;

import heesoon.tableManager.LikeMark.Service.LikeMarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LikeMarkController {

    private final LikeMarkService likeMarkService;

    @PostMapping("/like/{boardId}")
    public ResponseEntity<String> createLikeMark(@PathVariable Long boardId) {

        Long tmpMemberId = 1L;
        likeMarkService.processLikeMark(tmpMemberId, boardId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
