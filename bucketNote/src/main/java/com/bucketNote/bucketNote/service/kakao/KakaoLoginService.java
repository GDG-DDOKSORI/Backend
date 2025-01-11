package com.bucketNote.bucketNote.service.kakao;

import com.bucketNote.bucketNote.app.dto.KakaoLoginDto;

public interface KakaoLoginService {

    String getKakaoToken(String code);
    KakaoLoginDto.KakaoUserInfoDto getKakaoUserInfo(String token);

}
