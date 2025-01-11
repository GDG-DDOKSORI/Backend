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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
//        User user = uspackage com.bucketNote.bucketNote.service.vote;
//
//import com.bucketNote.bucketNote.domain.entity.BucketList;
//import com.bucketNote.bucketNote.domain.entity.User;
//import com.bucketNote.bucketNote.domain.entity.Vote;
//import com.bucketNote.bucketNote.repository.BucketListRepository;
//import com.bucketNote.bucketNote.repository.UserRepository;
//import com.bucketNote.bucketNote.repository.VoteRepository;
//import lombok.Builder;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class VoteServiceImpl implements VoteService {
//
//    private final VoteRepository voteRepository;
//    private final BucketListRepository bucketListRepository;
//    private final UserRepository userRepository;
////    @Override
////    public void voteOnBucketList(Long userId, Long bucketListId, Boolean isPossible) {
////        BucketList bucketList = bucketListRepository.findById(bucketListId)
////                .orElseThrow(() -> new IllegalArgumentException("버킷리스트가 존재하지 않습니다."));
////        User user = userRepository.findById(userId)
////                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
////
////        Optional<Vote> existingVote = voteRepository.findByBucketListIdAndUserId(bucketListId, userId);
////        if (existingVote.isPresent()) {
////            // 기존 투표가 있다면 수정
////            Vote vote = existingVote.get();
////            vote.setIsPossible(isPossible);
////            voteRepository.save(vote);
////        } else {
////            // 새 투표 추가
////            Vote vote = new Vote();
////            vote.setBucketListId(bucketListId);
////            vote.setReceiverId(userId);
////            vote.setIsPossible(isPossible);
////            voteRepository.save(vote);
////        }
////    }
////    @Override
////    public void addVote(Long voterId, Long receiverId, Long bucketListId) {
////        // 투표 기록 생성
////        Vote vote = Vote.builder()
////                .voterId(voterId)
////                .receiverId(receiverId)
////                .bucketListId(bucketListId)
////                .build();
////
////        voteRepository.save(vote);
////    }
//    @Override
//    public Map<String, Long> getVoteResults(Long bucketListId) {
//        // isPossible true 개수
//        Long trueCount = voteRepository.countByBucketListIdAndIsPossible(bucketListId, true);
//
//        // isPossible false 개수
//        Long falseCount = voteRepository.countByBucketListIdAndIsPossible(bucketListId, false);
//
//        // 결과 반환
//        Map<String, Long> results = new HashMap<>();
//        results.put("possibleCount", trueCount);
//        results.put("impossibleCount", falseCount);
//        return results;
//    }
//    public void voteOnBucketList(Long bucketListId, Long voterId, Boolean isPossible) {
//        Optional<Vote> existingVote = voteRepository.findByBucketListIdAndVoterIdAndReceiverId(bucketListId, voterId);
//
//        if (existingVote.isPresent()) {
//            throw new IllegalArgumentException("이미 투표한 버킷리스트입니다.");
//        }
//
//        Vote vote = Vote.builder()
//                .bucketListId(bucketListId)
//                .voterId(voterId)
//                .isPossible(isPossible)
//                .build();
//
//        voteRepository.save(vote);
//    }
//    @Override
//    public List<String> getVoterNamesByBucketListIdAndIsPossible(Long bucketListId, boolean isPossible) {
//        // 투표자의 ID 리스트 추출
//        List<Long> voterIds = voteRepository.findByBucketListIdAndIsPossible(bucketListId, isPossible)
//                .stream()
//                .map(vote -> vote.getVoterId())
//                .collect(Collectors.toList());
//
//        // ID로 유저 이름 리스트 가져오기
//        return userRepository.findAllById(voterIds)
//                .stream()
//                .map(User::getName)
//                .collect(Collectors.toList());
//    }
//}erRepository.findById(userId)
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
    public void voteOnBucketList(Long bucketListId, Long voterId, Boolean isPossible) {
        Optional<Vote> existingVote = voteRepository.findByBucketListIdAndVoterId(bucketListId, voterId);

        if (existingVote.isPresent()) {
            // 기존 투표가 있을 경우, isPossible 값이 다르면 업데이트
            Vote vote = existingVote.get();
            if (!vote.getIsPossible().equals(isPossible)) {
                vote.setIsPossible(isPossible);
                voteRepository.save(vote); // 업데이트 저장
            }
            else{
                throw new IllegalArgumentException(
                        String.format("이미 동일한 값으로 투표한 버킷리스트입니다. bucketListId: %d, voterId: %d", bucketListId, voterId)
                );
            }
        } else {
            // 기존 투표가 없을 경우 새로운 투표 추가
            Vote vote = Vote.builder()
                    .bucketListId(bucketListId)
                    .voterId(voterId)
                    .isPossible(isPossible)
                    .build();
            voteRepository.save(vote);
        }

    }
    @Override
    public List<String> getVoterNamesByBucketListIdAndIsPossible(Long bucketListId, boolean isPossible) {
        // 투표자의 ID 리스트 추출
        List<Long> voterIds = voteRepository.findByBucketListIdAndIsPossible(bucketListId, isPossible)
                .stream()
                .map(vote -> vote.getVoterId())
                .collect(Collectors.toList());

        // ID로 유저 이름 리스트 가져오기
        return userRepository.findAllById(voterIds)
                .stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }
}





