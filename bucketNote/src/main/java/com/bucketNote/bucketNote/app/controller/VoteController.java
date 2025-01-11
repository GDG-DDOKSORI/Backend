package com.bucketNote.bucketNote.app.controller;

import com.bucketNote.bucketNote.apiPayload.ApiResponse;
import com.bucketNote.bucketNote.apiPayload.Status;
import com.bucketNote.bucketNote.service.user.UserAccountService;
import com.bucketNote.bucketNote.service.vote.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "📊 투표", description = "버킷리스트 투표 API")
@RequestMapping("/api/vote")
public class VoteController {

    private final VoteService voteService;
    private final UserAccountService userAccountService;

    @PostMapping("/{bucketListId}")
    @Operation(summary = "버킷리스트에 대한 투표")
    public ApiResponse<?> voteOnBucketList(
            @RequestHeader("Authorization") String token,
            @PathVariable Long bucketListId,
            @RequestParam Boolean isPossible) {
        Long userId = userAccountService.getUserIdFromToken(token); // JWT에서 사용자 ID 추출
        voteService.voteOnBucketList(userId, bucketListId, isPossible);
        return ApiResponse.onSuccess(Status.VOTE_SUCCESS, null);
    }
}

