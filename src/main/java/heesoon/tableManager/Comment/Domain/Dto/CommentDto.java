package heesoon.tableManager.Comment.Domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class CommentDto {

    private Long commentId;

    @Size(max = 255, message = "댓글은 최대 255자 입니다.")
    private String comment;

    private boolean useYn;

    public CommentDto (Long commentId, String comment, boolean useYn) {
        this.commentId = commentId;
        this.comment = comment;
        this.useYn = useYn;
    }
}
