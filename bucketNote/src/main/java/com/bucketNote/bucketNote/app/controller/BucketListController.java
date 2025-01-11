package com.bucketNote.bucketNote.app.controller;

import com.bucketNote.bucketNote.apiPayload.ApiResponse;
import com.bucketNote.bucketNote.app.dto.BucketListDto;
import com.bucketNote.bucketNote.domain.entity.BucketList;
import com.bucketNote.bucketNote.service.bucketList.BucketListService;
import com.bucketNote.bucketNote.service.user.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.bucketNote.bucketNote.apiPayload.Status;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "📄 버킷리스트", description = "버킷리스트 관련 API")
@RequestMapping("/api/bucketList")
public class BucketListController {
    private final BucketListService bucketListService;
    private final UserAccountService userAccountService;
    @Operation(summary = "버킷리스트 수정")
    @PatchMapping("/edit")
    public ApiResponse<?> updateBucketList(
            @RequestHeader("Authorization") String token,
            @RequestBody BucketListDto.BucketListEditDto dto
    ) {
        Long userId = userAccountService.getUserIdFromToken(token); // Bearer 토큰에서 사용자 ID 추출
        bucketListService.updateBucketList(userId, dto.getBucketListId(), dto.getGoalText()); // 수정 요청
        return ApiResponse.onSuccess(Status.BUCKETLIST_UPDATE_SUCCESS, null);
    }

    @Operation(summary = "공개된 버킷리스트 조회")
    @GetMapping("/public-bucketlists")
    public ApiResponse<?> getBucketListsByUserId(@RequestParam Long userId) {
        List<BucketListDto.BucketListReadDto> bucketLists = bucketListService.getBucketListsByUserId(userId);
        return ApiResponse.onSuccess(Status.BUCKETLIST_READ_SUCCESS, bucketLists);
    }
    @Operation(summary = "해당 사용자의 모든 버킷리스트 조회")
    @GetMapping("/bucketlists")
    public ApiResponse<?> getBucketLists(@RequestHeader("Authorization") String token) {
        Long userId = userAccountService.getUserIdFromToken(token);
        List<BucketListDto.BucketListReadDto> bucketLists = bucketListService.getBucketListsByUserId(userId);
        return ApiResponse.onSuccess(Status.BUCKETLIST_READ_SUCCESS, bucketLists);
    }

    // 특정 ID의 버킷리스트 삭제
    @Operation(summary = "버킷리스트 삭제")
    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteBucketList(@RequestHeader("Authorization") String token,@PathVariable("id") Long id ) {
        Long userId = userAccountService.getUserIdFromToken(token); // 토큰에서 userId를 추출
        bucketListService.deleteBucketList(id, userId);
        return ApiResponse.onSuccess(Status.BUCKETLIST_DELETE_SUCCESS,null);
    }
    // 새 버킷리스트 생성
    @Operation(summary = "버킷리스트 생성")
    @PostMapping("/create")
    public ApiResponse<?> createBucketList(
            @RequestHeader("Authorization") String token,
            @RequestBody BucketListDto.BucketListCreateDto createDto // DTO로 받음
    ) {
        Long userId = userAccountService.getUserIdFromToken(token);
        BucketList bucketList = bucketListService.createBucketList(userId, createDto.getGoalText());
        return ApiResponse.onSuccess(Status.BUCKETLIST_UPDATE_SUCCESS, null);
    }
    @Operation(summary = "본인 버킷리스트 확인")
    @GetMapping("/{bucketListId}/is-owner")
    public ApiResponse<?> isOwner(
            @RequestHeader("Authorization") String token,
            @PathVariable Long bucketListId) {

        Long userId = userAccountService.getUserIdFromToken(token);
        // 소유 여부 확인
        boolean isOwner = bucketListService.validateBucketList(userId, bucketListId);
        return ApiResponse.onSuccess(Status.BUCKETLIST_CHECK_SUCCESS, isOwner);

    }
    @Operation(summary = "성공 여부 확인")
    @GetMapping("/{bucketListId}/is-achieved")
    public ApiResponse<?> isAchieved(@PathVariable Long bucketListId) {
        Boolean isAchieved = bucketListService.isAchieved(bucketListId);
        // null 값 처리
        if (isAchieved == null) {
            return ApiResponse.onSuccess(Status.BUCKETLIST_ACHIEVE_SUCCESS, null);
        }
        return ApiResponse.onSuccess(Status.BUCKETLIST_ACHIEVE_SUCCESS, isAchieved);
    }
    @Operation(summary = "성공 여부 변경")
    @PutMapping("/{bucketListId}/achieve")
    public ApiResponse<?> updateAchieveStatus(
            @RequestHeader("Authorization") String token,
            @PathVariable Long bucketListId,
            @RequestParam(required = false) boolean isAchieved) {
        Long userId = userAccountService.getUserIdFromToken(token);
        // 서비스 호출
        bucketListService.updateAchieveStatus(userId, bucketListId, isAchieved);
        return ApiResponse.onSuccess(Status.BUCKETLIST_UPDATE_SUCCESS, null);
    }


}
