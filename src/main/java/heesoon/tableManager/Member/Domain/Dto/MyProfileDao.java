package heesoon.tableManager.Member.Domain.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyProfileDao {

    private String nickname;
    private String birth;
    private String email;
    private String intro;
    private String pfUrl;
    private String provider;

    @Builder
    public MyProfileDao(String nickname, String birth, String email, String intro, String pfUrl, String provider) {
        this.nickname = nickname;
        this.birth = birth;
        this.email = email;
        this.intro = intro;
        this.pfUrl = pfUrl;
        this.provider = provider;
    }

}
