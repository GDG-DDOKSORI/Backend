package com.bucketNote.bucketNote.app.controller;

import com.bucketNote.bucketNote.apiPayload.ApiResponse;
import com.bucketNote.bucketNote.apiPayload.Status;
import com.bucketNote.bucketNote.app.dto.UserCustomDto;
import com.bucketNote.bucketNote.domain.entity.User;
import com.bucketNote.bucketNote.service.user.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "🧒🏻 유저", description = "유저 관련 API")
@RequestMapping("api/user")
public class UserController {

    private final UserAccountService userAccountService;

    @Operation(summary = "닉네임 변경")
    @PatchMapping("/nickname/edit")
    public ApiResponse<?> changeNickname(@RequestHeader("Authorization") String token, @RequestParam(value = "nickname") String nickname){
        Long userId = userAccountService.getUserIdFromToken(token);
        userAccountService.updateNickname(userId, nickname);
        return ApiResponse.onSuccess(Status.NICKNAME_SUCCESS, null);
    }

    @Operation(summary = "닉네임 가져오기")
    @PatchMapping("/nickname/get")
    public ApiResponse<?> getNickname(@RequestHeader("Authorization") String token){
        Long userId = userAccountService.getUserIdFromToken(token);
        String nickname = userAccountService.getNickname(userId);
        return ApiResponse.onSuccess(Status.NICKNAME_GET_SUCCESS, nickname);
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public ApiResponse<?> kakaoLogout(@RequestHeader("Authorization") String token){
        String actualToken = userAccountService.getActualTokenFromToken(token);
        userAccountService.logout(actualToken);
        return ApiResponse.onSuccess(Status.KAKAO_LOGOUT_SUCCESS, null);
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("leave")
    public ApiResponse<?> leave(@RequestHeader("Authorization") String token){
        Long userId = userAccountService.getUserIdFromToken(token);
        userAccountService.deleteUser(userId);
        return ApiResponse.onSuccess(Status.LEAVE_SUCCESS, null);
    }
    @Operation(summary = "ID 검색")
    @GetMapping("/api/user/search")
    public ApiResponse<?> searchUserIds(@RequestParam String keyword) {
        List<UserCustomDto.UserSearchDto> userIds = userAccountService.getUserIdsByKeyword(keyword);
        return ApiResponse.onSuccess(Status.USER_ID_PRESENT, userIds);
    }
    @Operation(summary = "이름 검색")
    @GetMapping("/api/user/search/name")
    public ApiResponse<?> searchUserName(@RequestParam Long id) {
        String name = userAccountService.getNickname(id);
        return ApiResponse.onSuccess(Status.NICKNAME_GET_SUCCESS, name);
    }
}

