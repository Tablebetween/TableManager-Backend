package heesoon.tableManager.Follow.Service;


import heesoon.tableManager.Follow.Domain.Dto.FollowDao;
import heesoon.tableManager.Follow.Domain.Dto.FollowDto;
import heesoon.tableManager.Follow.Domain.Follow;
import heesoon.tableManager.Follow.Repository.FollowRepository;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Repository.MemberRepository;
import heesoon.tableManager.toDoList.Domain.TodolistDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowServiceImpl implements FollowServce{
    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    @Override
    public void makeFollow(FollowDto followDto) {
        Member from = memberRepository.findById(followDto.getFollower()).orElse(null);
        Member to = memberRepository.findById(followDto.getFollowing()).orElse(null);
        Follow follow = Follow.builder()
                .following(to).follower(from).build();
        followRepository.save(follow);

    }

    @Override
    public List<FollowDao> loadMyFollower(Long userId) {
        Member cMember = memberRepository.findById(userId).orElse(null);
        // member의 게시글이 없을때의 예외 처리 필요
        List<Follow> follows = followRepository.findByFollowing(cMember);
        List<FollowDao> followDaos = follows.stream()
                .map(entity -> new FollowDao(entity.getFollower().getMemberId(),
                        entity.getFollower().getUsername(),entity.getUpdatedAt()))
                .sorted(Comparator.comparing(FollowDao::getUpdatedAt).reversed())
                .collect(Collectors.toList());
        return followDaos;
    }

    @Override
    public List<FollowDao> loadMyFollowing(Long userId) {
        Member cMember = memberRepository.findById(userId).orElse(null);
        List<Follow> follows = followRepository.findByFollower(cMember);
        List<FollowDao> followDaos = follows.stream()
                .map(entity -> new FollowDao(entity.getFollowing().getMemberId(),
                        entity.getFollowing().getUsername(),entity.getUpdatedAt()))
                .sorted(Comparator.comparing(FollowDao::getUpdatedAt).reversed())
                .collect(Collectors.toList());
        return followDaos;
    }

    @Override
    public void deleteFollow(FollowDto followDto) {
        Member from = memberRepository.findById(followDto.getFollower()).orElse(null);
        Member to = memberRepository.findById(followDto.getFollowing()).orElse(null);
        Follow follow = followRepository.findByFollowingAndFollower(to,from);
        followRepository.delete(follow);
    }
}
