package heesoon.tableManager.Comment.Service;

import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Repository.BoardRepository;
import heesoon.tableManager.Comment.Domain.Comment;
import heesoon.tableManager.Comment.Domain.Dto.CommentDto;
import heesoon.tableManager.Comment.Domain.Dto.UpdateCommentDto;
import heesoon.tableManager.Comment.Repository.CommentRepository;
import heesoon.tableManager.Exception.CustomException;
import heesoon.tableManager.Exception.ErrorCode;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    @Override
    public Comment createComment(Long boardId, Long memberId, CommentDto commentDto) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        //게시글 댓글 수 +1
        Board findBoard = boardRepository.findById(boardId).orElse(null);
        if (findBoard != null) {
            findBoard.addCommentCnt();
        }

        Comment createComment = Comment.builder()
                .comment(commentDto.getComment())
                .useYn(true)
                .board(findBoard)
                .member(findMember)
                .build();

        return commentRepository.save(createComment);
    }

    @Transactional
    @Override
    public void deleteComment(Long boardId, Long commentId) {
        //게시글 댓글 수 -1
        boardRepository.findById(boardId).ifPresent(findBoard -> findBoard.minusCommentCnt());
        commentRepository.findById(commentId).ifPresent(findComment -> findComment.setUseYn(false));
    }

    @Transactional
    @Override
    public void updateComment(Long commentId, UpdateCommentDto updateCommentDto) {
        commentRepository.findById(commentId).ifPresent(findComment -> findComment.setComment(updateCommentDto.getComment()));
    }
}
