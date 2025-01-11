package com.bucketNote.bucketNote.service.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bucketNote.bucketNote.app.dto.KakaoLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoLoginServiceImpl implements KakaoLoginService{

    @Value("${security.kakao.client_id:default_client_id}")
    String clientId;
    @Value("${security.kakao.redirect_uri:default_redirect_uri}")
    String redirectUri;

    @Override
    public String getKakaoToken(String code) {
        RestTemplate tokenRt = new RestTemplate(); // Http

        HttpHeaders tokenHeader = new HttpHeaders(); // Http header
        tokenHeader.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // key=value

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); // Http body
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, tokenHeader);

        ResponseEntity<String> response = tokenRt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        ); // Request to Kakao

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoLoginDto.KakaoTokenResponseDto tokenResponseDto = new KakaoLoginDto.KakaoTokenResponseDto();

        try{
            tokenResponseDto = objectMapper.readValue(response.getBody(), KakaoLoginDto.KakaoTokenResponseDto.class);
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return tokenResponseDto.getAccess_token();
    }

    @Override
    public KakaoLoginDto.KakaoUserInfoDto getKakaoUserInfo(String token) {
        RestTemplate userRt = new RestTemplate(); // Http

        HttpHeaders userHeader = new HttpHeaders(); // Http header
        userHeader.add("Authorization", "Bearer " + token); // Bearer + 토큰
        userHeader.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(userHeader);


        ResponseEntity<String> response = userRt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoLoginDto.KakaoUserInfoResponseDto kakaoUserInfoResponseDto = new KakaoLoginDto.KakaoUserInfoResponseDto();

        try{
            kakaoUserInfoResponseDto = objectMapper.readValue(response.getBody(), KakaoLoginDto.KakaoUserInfoResponseDto.class);
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        KakaoLoginDto.KakaoUserInfoDto dto = new KakaoLoginDto.KakaoUserInfoDto().builder()
                .email(kakaoUserInfoResponseDto.getKakao_account().getEmail())
                .build();

        return dto;
    }
}
