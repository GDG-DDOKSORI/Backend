package com.bucketNote.bucketNote.app.dto;

import lombok.*;

public class BucketListDto {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BucketListRequestDto{ // 카카오 토큰을 이용해서 읽어온 유저 정보
        private Long userId;
        private Long bucketListId;
        private String goalText;

    }
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BucketListReadDto {
        private Long id;
        private String goalText;
        private Integer createdYear;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BucketListCreateDto {
        private String goalText;
    }
}
