package com.bucketNote.bucketNote.service.user;

import com.bucketNote.bucketNote.app.dto.UserCustomDto;
import com.bucketNote.bucketNote.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserAccountService {
    Optional<User> saveKakaoUser(String email);
    Optional<User> findKakaoUserByEmail(String email);
    Optional<User> findById(Long id);
    Long getUserIdFromToken(String token);
    String getActualTokenFromToken(String token);
    void updateNickname(Long userId, String newNickname);
    void deleteUser(Long userId);
    void logout(String token);
    String getNickname(Long userId);
    public List<UserCustomDto.UserSearchDto> getUserIdsByKeyword(String keyword);
}
