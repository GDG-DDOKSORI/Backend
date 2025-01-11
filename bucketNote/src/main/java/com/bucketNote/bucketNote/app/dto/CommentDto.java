package com.bucketNote.bucketNote.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CommentDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResponseDto {
        private String content;
        private LocalDateTime createdAt;
        private Long userId;
        private String userName;


        // Getter, Setter 생략
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentRequestDto {
        private Long bucketListId; // 버킷리스트 ID
        private String content;    // 댓글 내용
    }


}
