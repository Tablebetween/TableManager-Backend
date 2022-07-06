package heesoon.tableManager.Follow.Service;

import heesoon.tableManager.Follow.Domain.Dto.FollowDao;
import heesoon.tableManager.Follow.Domain.Dto.FollowDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FollowServce {
    void makeFollow(FollowDto followDto);
    List<FollowDao> loadMyFollower(Long userId);
    List<FollowDao> loadMyFollowing(Long userId);
    void deleteFollow(FollowDto followDto);
}
