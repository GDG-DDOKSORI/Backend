package com.bucketNote.bucketNote.service.vote;

import com.bucketNote.bucketNote.apiPayload.exception.BucketListException;
import com.bucketNote.bucketNote.apiPayload.exception.UserException;
import com.bucketNote.bucketNote.apiPayload.exception.VoteException;
import com.bucketNote.bucketNote.domain.entity.BucketList;
import com.bucketNote.bucketNote.domain.entity.User;
import com.bucketNote.bucketNote.domain.entity.Vote;
import com.bucketNote.bucketNote.repository.BucketListRepository;
import com.bucketNote.bucketNote.repository.UserRepository;
import com.bucketNote.bucketNote.repository.VoteRepository;
import jakarta.transaction.Transactional;
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
@Transactional
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final BucketListRepository bucketListRepository;
    private final UserRepository userRepository;

    @Override
    public Map<String, Long> getVoteResults(Long bucketListId) {
        bucketListRepository.findById(bucketListId)
                .orElseThrow(() -> new BucketListException.BucketListNonExistsException("존재하지 않는 버킷리스트입니다. bucketListId: " + bucketListId));

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
        // 버킷리스트 존재 여부 확인
        BucketList bucketList = bucketListRepository.findById(bucketListId)
                .orElseThrow(() -> new BucketListException.BucketListNonExistsException("존재하지 않는 버킷리스트입니다. bucketListId: " + bucketListId));

        // 투표자 존재 여부 확인
        User voter = userRepository.findById(voterId)
                .orElseThrow(() -> new UserException.UserNonExistsException("존재하지 않는 사용자입니다. voterId: " + voterId));
        Optional<Vote> existingVote = voteRepository.findByBucketListIdAndVoterId(bucketListId, voterId);
        User receiver = bucketList.getUser();
        if (existingVote.isPresent()) {
            // 기존 투표가 있을 경우, isPossible 값이 다르면 업데이트
            Vote vote = existingVote.get();
            if (!vote.getIsPossible().equals(isPossible)) {
                vote.setIsPossible(isPossible);
                voteRepository.save(vote); // 업데이트 저장
            }
            else{
                throw new VoteException.VoteAlreadyExistException(
                        String.format("이미 동일한 값으로 투표한 버킷리스트입니다. bucketListId: %d, voterId: %d", bucketListId, voterId)
                );
            }
        } else {
            // 기존 투표가 없을 경우 새로운 투표 추가
            Vote vote = Vote.builder()
                    .bucketListId(bucketListId)
                    .voterId(voterId)
                    .receiverId(receiver.getId())
                    .isPossible(isPossible)
                    .build();
            voteRepository.save(vote);
        }

    }
    @Override
    public List<String> getVoterNamesByBucketListIdAndIsPossible(Long bucketListId, boolean isPossible) {
        // 버킷리스트 존재 여부 확인
        bucketListRepository.findById(bucketListId)
                .orElseThrow(() -> new BucketListException.BucketListNonExistsException("존재하지 않는 버킷리스트입니다. bucketListId: " + bucketListId));

        // 투표자의 ID 리스트 추출
        List<Long> voterIds = voteRepository.findByBucketListIdAndIsPossible(bucketListId, isPossible)
                .stream()
                .map(vote -> vote.getVoterId())
                .collect(Collectors.toList());
        if (voterIds.isEmpty()) {
            return List.of(); // 빈 리스트 반환
        }
        // ID로 유저 이름 리스트 가져오기
        return userRepository.findAllById(voterIds)
                .stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }
}





