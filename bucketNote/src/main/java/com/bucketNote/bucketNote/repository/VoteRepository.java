package com.bucketNote.bucketNote.repository;

import com.bucketNote.bucketNote.domain.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByBucketListIdAndReceiverId(Long bucketListId, Long receiverId);
    Optional<Vote> findByBucketListIdAndVoterId(Long bucketListId, Long voterId);
    Long countByBucketListIdAndIsPossible(Long bucketListId, Boolean isPossible);
    List<Vote> findByBucketListIdAndIsPossible(Long bucketListId, boolean isPossible);
}
