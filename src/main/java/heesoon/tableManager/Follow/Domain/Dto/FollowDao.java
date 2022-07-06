package heesoon.tableManager.Follow.Domain.Dto;

import heesoon.tableManager.Member.Domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FollowDao {
    private Long followMemberId;                    //맴버로 받으면 안됨
    private String followMemberName;
    private LocalDateTime updatedAt;

    @Builder
    public FollowDao(Long followMemberId,String followMemberName, LocalDateTime updatedAt)
    {

        this.followMemberId = followMemberId;
        this.followMemberName = followMemberName;
        this.updatedAt = updatedAt;
    }
}
