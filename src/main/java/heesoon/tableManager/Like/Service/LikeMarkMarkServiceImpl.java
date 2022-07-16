package heesoon.tableManager.Like.Service;

import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Repository.BoardRepository;
import heesoon.tableManager.Like.Domain.LikeMark;
import heesoon.tableManager.Like.Repository.LikeMarkRepository;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LikeMarkMarkServiceImpl implements LikeMarkService {

    private final LikeMarkRepository likeMarkRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    public void processLikeMark(Long memberId, Long boardId) {
        Member findMember = memberRepository.findById(memberId).orElse(null);
        Board findBoard = boardRepository.findById(boardId).orElse(null);

        LikeMark findLike = likeMarkRepository.findByMemberIdAndBoardId(findMember, findBoard).orElse(null);


        if (findLike == null) {
            LikeMark createLike = LikeMark.builder()
                    .memberId(findMember)
                    .boardId(findBoard)
                    .build();
            likeMarkRepository.save(createLike);

        } else {
            likeMarkRepository.delete(findLike);
        }
    }

}
