package com.bucketNote.bucketNote.service.comment;

import com.bucketNote.bucketNote.domain.entity.BucketList;
import com.bucketNote.bucketNote.domain.entity.Comment;
import com.bucketNote.bucketNote.domain.entity.User;
import com.bucketNote.bucketNote.repository.BucketListRepository;
import com.bucketNote.bucketNote.repository.CommentRepository;
import com.bucketNote.bucketNote.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BucketListRepository bucketListRepository;
    private final UserRepository userRepository;
    @Override
    public Comment addComment(Long userId, Long bucketListId, String content) {
        BucketList bucketList = bucketListRepository.findById(bucketListId)
                .orElseThrow(() -> new IllegalArgumentException("버킷리스트가 존재하지 않습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        Comment comment = new Comment();
        comment.setBucketList(bucketList);
        comment.setUser(user);
        comment.setContent(content);
        return commentRepository.save(comment);
    }
    @Override
    public List<Comment> getCommentsByBucketListId(Long bucketListId) {
        return commentRepository.findAllByBucketListId(bucketListId);
    }
}
