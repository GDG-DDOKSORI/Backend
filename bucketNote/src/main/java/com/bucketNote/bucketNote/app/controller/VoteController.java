package com.bucketNote.bucketNote.app.controller;

import com.bucketNote.bucketNote.apiPayload.ApiResponse;
import com.bucketNote.bucketNote.apiPayload.Status;
import com.bucketNote.bucketNote.service.bucketList.BucketListService;
import com.bucketNote.bucketNote.service.user.UserAccountService;
import com.bucketNote.bucketNote.service.vote.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/votes")
@Tag(name = "🗳️ 투표", description = "버킷리스트 투표 관련 API")
public class VoteController {

    private final UserAccountService userAccountService; // 사용자 서비스
    private final BucketListService bucketListService;  // 버킷리스트 서비스
    private final VoteService voteService;              // 투표 서비스

    @Operation(summary = "버킷리스트에 투표")
    @PostMapping("/{receiverId}/{bucketListId}")
    public ApiResponse<?> voteOnBucketList(
            @RequestHeader("Authorization") String token,
            @RequestParam Long bucketListId,
            @RequestParam Boolean isPossible) {
        Long voterId = userAccountService.getUserIdFromToken(token);
        // 받는 사용자의 버킷리스트가 존재하는지 확인
        boolean isBucketListValid = bucketListService.validateBucketList(voterId, bucketListId);
        if (!isBucketListValid) {
            return ApiResponse.onFailure(Status.BUCKETLIST_NOT_FOUND, null);
        }
        voteService.voteOnBucketList(bucketListId, voterId, isPossible);

        return ApiResponse.onSuccess(Status.VOTE_SUCCESS,null);
    }
    @Operation(summary = "특정 버킷리스트의 투표 결과 조회")
    @GetMapping("/{bucketListId}/results")
    public ApiResponse<?> getVoteResults(
            @PathVariable("bucketListId") Long bucketListId // 조회할 버킷리스트 ID
    ) {
        // 투표 결과 조회
        Map<String, Long> results = voteService.getVoteResults(bucketListId);

        return ApiResponse.onSuccess(Status.VOTE_RESULTS_FETCH_SUCCESS, results);
    }
    @Operation(summary = "투표에 따른 투표자 이름 조회")
    @GetMapping("/names")
    public ResponseEntity<List<String>> getVoterNames(
            @RequestParam Long bucketListId,
            @RequestParam boolean isPossible) {
        List<String> voterNames = voteService.getVoterNamesByBucketListIdAndIsPossible(bucketListId, isPossible);
        return ResponseEntity.ok(voterNames);
    }
}


