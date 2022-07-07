package heesoon.tableManager.Member.Domain.Dto;

import heesoon.tableManager.Follow.Domain.Follow;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MyPageDao {
    private String name;
    private String birth;
    private Integer following;
    private Integer follower;
    private String intro;
    //이름, 생일, 사진이 없음, 팔로잉 팔로워, intro
    @Builder
    public MyPageDao(String name, String birth, Integer following, Integer follower, String intro)
    {
        this.name = name;
        this.birth = birth;
        this.following = following;
        this.follower = follower;
        this.intro = intro;
    }
}
