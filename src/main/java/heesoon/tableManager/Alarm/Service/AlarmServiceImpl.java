package heesoon.tableManager.Alarm.Service;

import heesoon.tableManager.Alarm.Domain.AlarmDao;
import heesoon.tableManager.Alarm.Domain.AlarmDto;
import heesoon.tableManager.Alarm.Repository.AlarmRepository;
import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Repository.BoardRepository;
import heesoon.tableManager.Comment.Domain.Comment;
import heesoon.tableManager.Comment.Repository.CommentRepository;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AlarmServiceImpl implements AlarmService{
    private AlarmRepository alarmRepository;
    private CommentRepository commentRepository;
    private MemberRepository memberRepository;
    private BoardRepository boardRepository;

    @Override
    public AlarmDao makeAlarm(AlarmDto alarmDto) {
        Member sendMember = memberRepository.findById(alarmDto.getSendMemberId()).orElse(null);
        Member receiveMember = memberRepository.findById(alarmDto.getReceiveMember()).orElse(null);
        Long status = alarmDto.getStatusId();
        Long content = alarmDto.getContentId();
        AlarmDao alarmDao;
        if (status == 1 || status == 2)             //누군가 나에게 게시글에 좋아요 or 누군가 나에게 게시글에 댓글
        {
            Board board = boardRepository.findById(content).orElse(null);
            alarmDao = AlarmDao.builder().sendUser(sendMember.getName())
                    .content(board).statusId(status)
                    .receiveUser(receiveMember.getName()).build();
        }
        else
        {
            Comment comment = commentRepository.findById(content).orElse(null);
            alarmDao = AlarmDao.builder().sendUser(sendMember.getName())
                    .content(comment).statusId(status)
                    .receiveUser(receiveMember.getName()).build();

        }
        return alarmDao;
    }
}
