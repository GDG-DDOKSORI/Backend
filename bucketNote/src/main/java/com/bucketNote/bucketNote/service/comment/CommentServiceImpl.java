package com.bucketNote.bucketNote.service.comment;

import com.bucketNote.bucketNote.app.dto.BucketListDto;
import com.bucketNote.bucketNote.app.dto.CommentDto;
import com.bucketNote.bucketNote.domain.entity.BucketList;
import com.bucketNote.bucketNote.domain.entity.Comment;
import com.bucketNote.bucketNote.domain.entity.User;
import com.bucketNote.bucketNote.repository.BucketListRepository;
import com.bucketNote.bucketNote.repository.CommentRepository;
import com.bucketNote.bucketNote.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BucketListRepository bucketListRepository;
    private final UserRepository userRepository;

    @Override
    // 댓글 리스트 조회
    public List<CommentDto.CommentResponseDto> getCommentsByBucketListId(Long bucketListId) {
        return commentRepository.findAllByBucketListId(bucketListId).stream()
                .map(comment -> new CommentDto.CommentResponseDto(
                        comment.getContent(), // String
                        comment.getCreatedAt(), // LocalDateTime
                        comment.getUser().getId(), // Long
                        comment.getUser().getName() // String
                ))
                .collect(Collectors.toList());
    }
    // 댓글 추가
    public void addComment(Long userId, CommentDto.CommentRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        BucketList bucketList = bucketListRepository.findById(requestDto.getBucketListId())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 버킷리스트입니다."));

        // 새로운 댓글 생성
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setBucketList(bucketList);
        comment.setContent(requestDto.getContent());
        comment.setCreatedAt(LocalDateTime.now()); // 현재 시간 설정

        // 댓글 저장
        commentRepository.save(comment);


    }
}
