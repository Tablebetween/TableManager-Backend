package heesoon.tableManager.Follow.Domain.Dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FollowDto {
    private Long following;
    private Long follower;

    public FollowDto (Long following,Long follower){
        this.follower = follower;
        this.following = following;
    }

}
