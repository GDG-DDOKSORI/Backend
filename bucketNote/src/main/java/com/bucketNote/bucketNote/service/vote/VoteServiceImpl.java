package com.bucketNote.bucketNote.service.vote;

import com.bucketNote.bucketNote.domain.entity.BucketList;
import com.bucketNote.bucketNote.domain.entity.User;
import com.bucketNote.bucketNote.domain.entity.Vote;
import com.bucketNote.bucketNote.repository.BucketListRepository;
import com.bucketNote.bucketNote.repository.UserRepository;
import com.bucketNote.bucketNote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final BucketListRepository bucketListRepository;
    private final UserRepository userRepository;
    @Override
    public void voteOnBucketList(Long userId, Long bucketListId, Boolean isPossible) {
        BucketList bucketList = bucketListRepository.findById(bucketListId)
                .orElseThrow(() -> new IllegalArgumentException("버킷리스트가 존재하지 않습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        Optional<Vote> existingVote = voteRepository.findByBucketListIdAndUserId(bucketListId, userId);
        if (existingVote.isPresent()) {
            // 기존 투표가 있다면 수정
            Vote vote = existingVote.get();
            vote.setIsPossible(isPossible);
            voteRepository.save(vote);
        } else {
            // 새 투표 추가
            Vote vote = new Vote();
            vote.setBucketList(bucketList);
            vote.setUser(user);
            vote.setIsPossible(isPossible);
            voteRepository.save(vote);
        }
    }
}
