package com.bucketNote.bucketNote.apiPayload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum Status {
    //예시
    TEMP_SUCCESS("200", "SUCCESS", "임시 API 접근에 성공했습니다."),

    // TOKEN 관련
    TOKEN_INVALID("401", "FAILURE", "유효하지 않는 토큰입니다."),
    TOKEN_EXPIRED("401", "FAILURE", "만료된 토큰입니다."),

    // USER 관련
    USER_NON_PRESENT("404", "FAILURE", "존재하지 않는 유저입니다."),
    KAKAO_CODE_SUCCESS("200", "SUCCESS", "카카오 인가코드를 발급받았습니다."),
    KAKAO_LOGIN_SUCCESS("200", "SUCCESS", "카카오 로그인을 성공했습니다."),
    KAKAO_LOGOUT_SUCCESS("200", "SUCCESS", "카카오 로그아웃을 성공했습니다."),
    NICKNAME_SUCCESS("200", "SUCCESS", "닉네임을 변경했습니다."),
    NICKNAME_GET_SUCCESS("200", "SUCCESS", "닉네임을 읽었습니다."),
    NICKNAME_DUPLICATE("409", "FAILURE", "이미 존재하는 닉네임입니다."),
    LEAVE_SUCCESS("200", "SUCCESS", "회원 탈퇴를 완료했습니다.");
    private final String code;
    private final String result;
    private final String message;
}
