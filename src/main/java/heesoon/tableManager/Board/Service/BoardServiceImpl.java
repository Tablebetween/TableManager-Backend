package heesoon.tableManager.Board.Service;

import heesoon.tableManager.AWSS3.S3Service.S3uploader;
import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Domain.BoardDto;
import heesoon.tableManager.Board.Domain.BoardDao;
import heesoon.tableManager.Board.Repository.BoardRepository;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Repository.MemberRepository;
import heesoon.tableManager.toDoList.Domain.TodolistDao;
import lombok.RequiredArgsConstructor;
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
public class BoardServiceImpl implements BoardService{
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final S3uploader s3uploader;

    @Override
    public Board makeBoard(BoardDto boardDto, MultipartFile file) throws IOException, ParseException {
        String imagepath = s3uploader.upload(file,"static");
        Member cmember = memberRepository.findById(boardDto.getMemberId()).orElse(null);
        Board board = Board.builder().img_url(imagepath).content(boardDto.getContent()).memberId(cmember).build();
        boardRepository.save(board);
        return board;
    }

    @Override
    public List<BoardDao> loadMyBoardById(Long id) {
        Member cmember = memberRepository.findById(id).orElse(null);
        // member의 게시글이 없을때의 예외 처리 필요
        List<Board> boards = boardRepository.findBymemberId(cmember);
        List<BoardDao> boardDtos = boards.stream()
                .map(o -> new BoardDao(o))
                .filter(use -> use.isUse_yn() == false)
                .sorted(Comparator.comparing(BoardDao::getUpdatedAt).reversed())
                .collect(Collectors.toList());
//        List<BoardDao> boardDtos = new ArrayList<>();
//        for(int i = 0;i<boards.size();i++)
//        {
//            if (boards.get(i).isUse_yn() == true)
//            {
//                continue;
//            }
//            else
//            {
//                BoardDao boardInfo = new BoardDao().toDto(boards.get(i));
//                boardDtos.add(boardInfo);            }
//        }
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
