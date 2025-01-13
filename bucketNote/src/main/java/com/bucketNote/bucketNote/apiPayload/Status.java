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
    LEAVE_SUCCESS("200", "SUCCESS", "회원 탈퇴를 완료했습니다."),
    USER_ID_PRESENT("200", "SUCCESS", "유저 아이디 반환을 성공했습니다."),
    //버킷리스트
    BUCKETLIST_UPDATE_SUCCESS("200", "SUCCESS", "버킷리스트를 업데이트했습니다."),
    BUCKETLIST_DELETE_SUCCESS("200", "SUCCESS", "버킷리스트를 삭제했습니다."),
    BUCKETLIST_NOT_FOUND("404", "FAILURE", "버킷리스트가 존재하지 않습니다."),
    BUCKETLIST_READ_SUCCESS("200", "SUCCESS", "버킷리스트를 읽어왔습니다."),
    BUCKETLIST_CHECK_SUCCESS("200", "SUCCESS", "버킷리스트를 본인 것인지 확인했습니다."),
    BUCKETLIST_ACHIEVE_SUCCESS("200", "SUCCESS", "버킷리스트 성공 여부 확인했습니다."),
    BUCKETLIST_NON_PRESENT("404", "FAILURE", "존재하지 않는 버킷리스트입니다."),
    BUCKETLIST_NO_BY_USER("404", "FAILURE", "해당 사용자가 소유한 버킷리스트가 아닙니다"),

    COMMENT_ADD_SUCCESS("200", "SUCCESS", "응원글 추가를 성공했습니다."),
    COMMENT_READ_SUCCESS("200", "SUCCESS", "응원글 조회를 성공했습니다."),
    COMMENT_NON_FOUND("404", "FAILURE", "댓글을 입력하십시오"),
    COMMENT_NON("404", "FAILURE", "해당 버킷리스트에 댓글이 없습니다."),
    VOTE_ALREADY_EXSISTS("404", "FAILURE", "중복 투표는 불가합니다."),
    VOTE_RESULTS_FETCH_SUCCESS("200", "SUCCESS", "투표 조회 성공했습니다"),
    VOTE_NOT_ALLOWED("403", "FAILURE", "본인의 버킷리스트에는 투표할 수 없습니다."),
    VOTE_SUCCESS("200", "SUCCESS", "투표에 성공했습니다");
    private final String code;
    private final String result;
    private final String message;
}
