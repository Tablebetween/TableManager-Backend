package heesoon.tableManager.Board.Service;

import heesoon.tableManager.AWSS3.S3Service.S3uploader;
import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Domain.BoardDto;
import heesoon.tableManager.Board.Domain.BoardDao;
import heesoon.tableManager.Board.Repository.BoardRepository;
import heesoon.tableManager.Event.FileEvent;
import heesoon.tableManager.Event.FileEventPublisher;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Repository.MemberRepository;
import heesoon.tableManager.toDoList.Domain.TodolistDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BoardServiceImpl implements BoardService{
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final S3uploader s3uploader;
    private final FileEventPublisher eventPublisher;

    @Override
    public Board makeBoard(BoardDto boardDto, MultipartFile file) throws IOException, ParseException {
        String imagePath = s3uploader.upload(file,"static");
        Member cMember = memberRepository.findById(boardDto.getMemberId()).orElse(null);
        Board board = Board.builder().img_url(imagePath).content(boardDto.getContent()).memberId(cMember).build();
        log.info("board = {}", cMember);
        boardRepository.save(board);
        FileEvent fileEvent = FileEvent.toCompleteEvent(cMember,board);
        eventPublisher.notifyComplete(fileEvent);
        return board;
    }

    @Override
    public List<BoardDao> loadMyBoardById(Long id) {
        Member cmember = memberRepository.findById(id).orElse(null);
        // member??? ???????????? ???????????? ?????? ?????? ??????
        List<Board> boards = boardRepository.findBymemberId(cmember);
        List<BoardDao> boardDtos = boards.stream()
                .map(o -> new BoardDao(o))
                .filter(use -> use.isUse_yn() == false)
                .sorted(Comparator.comparing(BoardDao::getUpdatedAt).reversed())
                .collect(Collectors.toList());

        return boardDtos;
    }

    @Override
    public BoardDao loadboardbyid(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        BoardDao info = new BoardDao().toDto(board);
        return info;
    }

    @Override
    public void boardDelete(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        board.setUse_yn(true);
    }

    @Override
    public void boardUpdate(Long id, BoardDto boardDto) {
        boardRepository.findById(id).map(entity -> entity.updateBoard(boardDto.getContent())).orElse(null);
    }

}
