package heesoon.tableManager.Alarm.Service;

import heesoon.tableManager.Alarm.Domain.Alarm;
import heesoon.tableManager.Alarm.Domain.AlarmDao;
import heesoon.tableManager.Alarm.Domain.AlarmDto;
import heesoon.tableManager.Alarm.Repository.AlarmRepository;
import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Repository.BoardRepository;
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
    private MemberRepository memberRepository;
    private BoardRepository boardRepository;

    @Override
    public AlarmDao makeAlarm(AlarmDto alarmDto) {
        Member sendMember = memberRepository.findById(alarmDto.getSendMemberId()).orElse(null);
        Member receiveMember = memberRepository.findById(alarmDto.getReceiveMember()).orElse(null);
        Long status = alarmDto.getStatusId();
        Long content = alarmDto.getContentId();
        AlarmDao alarmDao = new AlarmDao();
        if (status == 1 || status == 2)
        {
            Board board = boardRepository.findById(content).orElse(null);
            alarmDao = AlarmDao.builder().sendUser(sendMember.getName())
                    .content(board.getContent()).statusId(status)
                    .receiveUser(receiveMember.getName()).build();

        }
        else
        {

        }

        return alarmDao;
    }
}
