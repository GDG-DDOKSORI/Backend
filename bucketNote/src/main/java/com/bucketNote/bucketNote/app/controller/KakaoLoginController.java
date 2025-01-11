package com.bucketNote.bucketNote.app.controller;

import com.bucketNote.bucketNote.apiPayload.ApiResponse;
import com.bucketNote.bucketNote.apiPayload.Status;
import com.bucketNote.bucketNote.app.dto.KakaoLoginDto;
import com.bucketNote.bucketNote.domain.entity.User;
import com.bucketNote.bucketNote.jwt.JWTUtil;
import com.bucketNote.bucketNote.service.kakao.KakaoLoginService;
import com.bucketNote.bucketNote.service.user.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "🍫 카카오", description = "카카오 관련 API")
@RequestMapping("api/kakao")
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;
    private final UserAccountService userAccountService;
    private final JWTUtil jwtUtil;

    @Operation(summary = "[사용안함]")
    @GetMapping("/callback")
    public ApiResponse<?> callback(@RequestParam("code") String code){
        return ApiResponse.onSuccess(Status.KAKAO_CODE_SUCCESS, code);
    }

    @Operation(summary = "로그인")
    @Parameter(name = "code", description = "카카오에서 받은 인카코드, RequestParam")
    @PostMapping("/code/login")
    public ApiResponse<?> kakaoCodeLogin(@RequestParam("code") String code) {
        String token = kakaoLoginService.getKakaoToken(code);
        KakaoLoginDto.KakaoUserInfoDto kakaoUserInfoDto = kakaoLoginService.getKakaoUserInfo(token);
        if(userAccountService.findKakaoUserByEmail(kakaoUserInfoDto.getEmail()).isEmpty()) userAccountService.saveKakaoUser(kakaoUserInfoDto.getEmail());
        User user = userAccountService.findKakaoUserByEmail(kakaoUserInfoDto.getEmail()).get();

        String jwtToken = jwtUtil.createJwt(user.getId(), 36000000000000L);
        KakaoLoginDto.LoginResponseDto dto = new KakaoLoginDto.LoginResponseDto().builder()
                .userId(user.getId())
                .token(jwtToken)
                .build();

        return ApiResponse.onSuccess(Status.KAKAO_LOGIN_SUCCESS, dto);
    }

    @Operation(summary = "로그인")
    @Parameter(name = "token", description = "카카오에서 받은 토큰, RequestParam")
    @PostMapping("/token/login")
    public ApiResponse<?> kakaoTokenLogin(@RequestParam("token") String token) {

        KakaoLoginDto.KakaoUserInfoDto kakaoUserInfoDto = kakaoLoginService.getKakaoUserInfo(token);
        if(userAccountService.findKakaoUserByEmail(kakaoUserInfoDto.getEmail()).isEmpty()) userAccountService.saveKakaoUser(kakaoUserInfoDto.getEmail());
        User user = userAccountService.findKakaoUserByEmail(kakaoUserInfoDto.getEmail()).get();

        String jwtToken = jwtUtil.createJwt(user.getId(), 36000000000000L);
        KakaoLoginDto.LoginResponseDto dto = new KakaoLoginDto.LoginResponseDto().builder()
                .userId(user.getId())
                .token(jwtToken)
                .build();

        return ApiResponse.onSuccess(Status.KAKAO_LOGIN_SUCCESS, dto);
    }
}
