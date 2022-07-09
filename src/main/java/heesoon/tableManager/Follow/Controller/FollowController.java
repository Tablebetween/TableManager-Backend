package heesoon.tableManager.Follow.Controller;

import heesoon.tableManager.Follow.Domain.Dto.FollowDao;
import heesoon.tableManager.Follow.Domain.Dto.FollowDto;
import heesoon.tableManager.Follow.Service.FollowServce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {
    private final FollowServce followServce;

    @GetMapping("/follower/{userId}")
    ResponseEntity<?> loadMyFollower(@PathVariable Long userId)
    {
        List<FollowDao> followDaoList = followServce.loadMyFollower(userId);
        return ResponseEntity.ok(followDaoList);
    }
    @GetMapping("/following/{userId}")
    ResponseEntity<?> loadMyFollowing(@PathVariable Long userId)
    {
        List<FollowDao> followDaoList = followServce.loadMyFollowing(userId);
        return ResponseEntity.ok(followDaoList);
    }
    @PostMapping
    ResponseEntity<?> makeFollow(@RequestBody FollowDto followDto)
    {
        followServce.makeFollow(followDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @DeleteMapping
    ResponseEntity<?> deleteFollow(@RequestBody FollowDto followDto)
    {
        followServce.deleteFollow(followDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
