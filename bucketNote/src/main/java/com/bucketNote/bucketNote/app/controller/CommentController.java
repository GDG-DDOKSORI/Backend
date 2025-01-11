package com.bucketNote.bucketNote.app.controller;

import com.bucketNote.bucketNote.apiPayload.ApiResponse;
import com.bucketNote.bucketNote.app.dto.BucketListDto;
import com.bucketNote.bucketNote.app.dto.CommentDto;
import com.bucketNote.bucketNote.domain.entity.Comment;
import com.bucketNote.bucketNote.service.comment.CommentService;
import com.bucketNote.bucketNote.service.user.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.bucketNote.bucketNote.apiPayload.Status;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "💬 댓글", description = "버킷리스트 댓글 API")
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;
    private final UserAccountService userAccountService;

    @PostMapping("/{bucketListId}")
    @Operation(summary = "댓글 추가")
    public ApiResponse<?> addComment(
            @RequestHeader("Authorization") String token,
            @RequestBody CommentDto.CommentRequestDto requestDto) {
        Long userId = userAccountService.getUserIdFromToken(token); // JWT에서 사용자 ID 추출
        commentService.addComment(userId, new CommentDto.CommentRequestDto(requestDto.getBucketListId(), requestDto.getContent()));
        return ApiResponse.onSuccess(Status.COMMENT_ADD_SUCCESS, null); // 성공 메시지만 반환
    }

    @GetMapping("/{bucketListId}")
    @Operation(summary = "댓글 조회")
    public ApiResponse<?> getComments(@PathVariable Long bucketListId) {
        List<CommentDto.CommentResponseDto> comments = commentService.getCommentsByBucketListId(bucketListId);
        return ApiResponse.onSuccess(Status.COMMENT_READ_SUCCESS, comments);
    }
}

