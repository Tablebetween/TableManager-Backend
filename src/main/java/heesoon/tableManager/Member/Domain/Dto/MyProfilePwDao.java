package heesoon.tableManager.Member.Domain.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyProfilePwDao {

    private String password;

    @Builder
    public MyProfilePwDao(String password) {
        this.password = password;
    }

}
