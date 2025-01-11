package com.bucketNote.bucketNote.service.comment;

import com.bucketNote.bucketNote.domain.entity.Comment;

import java.util.List;

public interface CommentService {
    public Comment addComment(Long userId, Long bucketListId, String content);
    public List<Comment> getCommentsByBucketListId(Long bucketListId);
}
