package com.bucketNote.bucketNote.repository;

import com.bucketNote.bucketNote.domain.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByBucketListIdAndUserId(Long bucketListId, Long userId); // 특정 사용자의 특정 버킷리스트에 대한 투표 조회
}
