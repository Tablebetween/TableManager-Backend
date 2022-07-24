package heesoon.tableManager.Comment.Domain.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
public class CommentDto {

    @Size(max = 255, message = "댓글은 최대 255자 입니다.")
    private String comment;

    private boolean useYn;

    public CommentDto (String comment, boolean useYn) {
        this.comment = comment;
        this.useYn = useYn;
    }
}
