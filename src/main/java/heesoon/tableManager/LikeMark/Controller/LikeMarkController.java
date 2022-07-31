package heesoon.tableManager.LikeMark.Controller;

import heesoon.tableManager.LikeMark.Service.LikeMarkService;
import heesoon.tableManager.Security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LikeMarkController {

    private final LikeMarkService likeMarkService;

    @PostMapping("/board/{boardId}/likeMark")
    public ResponseEntity<String> createLikeMark(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long boardId) {

        Long memberId = principalDetails.getMember().getMemberId();
        likeMarkService.processLikeMark(memberId, boardId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
