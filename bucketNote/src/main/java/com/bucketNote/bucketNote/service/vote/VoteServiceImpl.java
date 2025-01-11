package com.bucketNote.bucketNote.service.vote;

import com.bucketNote.bucketNote.domain.entity.BucketList;
import com.bucketNote.bucketNote.domain.entity.User;
import com.bucketNote.bucketNote.domain.entity.Vote;
import com.bucketNote.bucketNote.repository.BucketListRepository;
import com.bucketNote.bucketNote.repository.UserRepository;
import com.bucketNote.bucketNote.repository.VoteRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final BucketListRepository bucketListRepository;
    private final UserRepository userRepository;
//    @Override
//    public void voteOnBucketList(Long userId, Long bucketListId, Boolean isPossible) {
//        BucketList bucketList = bucketListRepository.findById(bucketListId)
//                .orElseThrow(() -> new IllegalArgumentException("버킷리스트가 존재하지 않습니다."));
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
//
//        Optional<Vote> existingVote = voteRepository.findByBucketListIdAndUserId(bucketListId, userId);
//        if (existingVote.isPresent()) {
//            // 기존 투표가 있다면 수정
//            Vote vote = existingVote.get();
//            vote.setIsPossible(isPossible);
//            voteRepository.save(vote);
//        } else {
//            // 새 투표 추가
//            Vote vote = new Vote();
//            vote.setBucketListId(bucketListId);
//            vote.setReceiverId(userId);
//            vote.setIsPossible(isPossible);
//            voteRepository.save(vote);
//        }
//    }
//    @Override
//    public void addVote(Long voterId, Long receiverId, Long bucketListId) {
//        // 투표 기록 생성
//        Vote vote = Vote.builder()
//                .voterId(voterId)
//                .receiverId(receiverId)
//                .bucketListId(bucketListId)
//                .build();
//
//        voteRepository.save(vote);
//    }
    @Override
    public Map<String, Long> getVoteResults(Long bucketListId) {
        // isPossible true 개수
        Long trueCount = voteRepository.countByBucketListIdAndIsPossible(bucketListId, true);

        // isPossible false 개수
        Long falseCount = voteRepository.countByBucketListIdAndIsPossible(bucketListId, false);

        // 결과 반환
        Map<String, Long> results = new HashMap<>();
        results.put("possibleCount", trueCount);
        results.put("impossibleCount", falseCount);
        return results;
    }
    public void voteOnBucketList(Long bucketListId, Long voterId, Long receiverId, Boolean isPossible) {
        Optional<Vote> existingVote = voteRepository.findByBucketListIdAndVoterIdAndReceiverId(bucketListId, voterId, receiverId);

        if (existingVote.isPresent()) {
            throw new IllegalArgumentException("이미 투표한 버킷리스트입니다.");
        }

        Vote vote = Vote.builder()
                .bucketListId(bucketListId)
                .voterId(voterId)
                .receiverId(receiverId)
                .isPossible(isPossible)
                .build();

        voteRepository.save(vote);
    }





}
