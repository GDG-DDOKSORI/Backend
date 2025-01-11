package com.bucketNote.bucketNote.repository;

import com.bucketNote.bucketNote.domain.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByBucketListIdAndReceiverId(Long bucketListId, Long receiverId);
    Optional<Vote> findByBucketListIdAndVoterIdAndReceiverId(Long bucketListId, Long voterId, Long receiverId);
    Long countByBucketListIdAndIsPossible(Long bucketListId, Boolean isPossible);
}
