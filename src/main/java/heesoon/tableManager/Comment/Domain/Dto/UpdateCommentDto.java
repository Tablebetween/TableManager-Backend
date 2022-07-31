package heesoon.tableManager.Comment.Domain.Dto;

import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class UpdateCommentDto {

    @Size(max = 255, message = "댓글은 최대 255자 입니다.")
    private String comment;

}
