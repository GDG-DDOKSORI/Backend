package com.bucketNote.bucketNote.app.controller;

import com.bucketNote.bucketNote.apiPayload.ApiResponse;
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
            @PathVariable Long bucketListId,
            @RequestParam String content) {
        Long userId = userAccountService.getUserIdFromToken(token); // JWT에서 사용자 ID 추출
        Comment comment = commentService.addComment(userId, bucketListId, content);
        return ApiResponse.onSuccess(Status.COMMENT_ADD_SUCCESS, comment);
    }

    @GetMapping("/{bucketListId}")
    @Operation(summary = "댓글 조회")
    public ApiResponse<?> getComments(@PathVariable Long bucketListId) {
        List<Comment> comments = commentService.getCommentsByBucketListId(bucketListId);
        return ApiResponse.onSuccess(Status.COMMENT_READ_SUCCESS, comments);
    }
}

