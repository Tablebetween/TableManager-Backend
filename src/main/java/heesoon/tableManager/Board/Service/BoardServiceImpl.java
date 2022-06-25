package heesoon.tableManager.Board.Service;

import heesoon.tableManager.AWSS3.S3Service.S3uploader;
import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Domain.BoardDto;
import heesoon.tableManager.Board.Repository.BoardRepository;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Repository.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final S3uploader s3uploader;

    @Override
    public Board makeboard(BoardDto boardDto, MultipartFile file) throws IOException, ParseException {
        String imagepath = s3uploader.upload(file,"static");
        Member cmember = memberRepository.findById(boardDto.getMemberId()).orElse(null);
        LocalDate now = LocalDate.now();
        Board board = Board.builder().img_url(imagepath).content(boardDto.getContent())
                .inp_dthms(now.toString()).memberId(cmember).build();
        boardRepository.save(board);
        return board;
    }
}
