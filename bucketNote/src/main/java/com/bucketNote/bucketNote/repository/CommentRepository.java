package com.bucketNote.bucketNote.repository;

import com.bucketNote.bucketNote.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBucketListId(Long bucketListId); // 특정 버킷리스트의 모든 댓글 조회
}
