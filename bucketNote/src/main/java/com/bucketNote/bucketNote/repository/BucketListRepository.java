package com.bucketNote.bucketNote.repository;

import com.bucketNote.bucketNote.domain.entity.BucketList;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;


public interface BucketListRepository extends JpaRepository<BucketList, Long> {

    // 특정 사용자의 모든 버킷리스트 조회
    // 특정 사용자의 모든 버킷리스트 조회
    List<BucketList> findAllByUserId(Long userId);

    // 특정 ID와 사용자 ID로 버킷리스트 조회
    Optional<BucketList> findByIdAndUserId(Long id, Long userId);

    // 존재 여부 확인
    boolean existsByIdAndUserId(Long id, Long userId);

}

