package com.bucketNote.bucketNote.service.bucketList;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.bucketNote.bucketNote.apiPayload.exception.BucketListException;
import com.bucketNote.bucketNote.apiPayload.exception.UserException;
import com.bucketNote.bucketNote.app.dto.BucketListDto;
import com.bucketNote.bucketNote.domain.entity.BucketList;
import com.bucketNote.bucketNote.domain.entity.User;
import com.bucketNote.bucketNote.repository.BucketListRepository;
import com.bucketNote.bucketNote.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BucketListServiceImpl implements BucketListService{

    private final BucketListRepository bucketListRepository;
    private final UserRepository userRepository;
    // 특정 사용자의 모든 버킷리스트 조회
    public List<BucketListDto.BucketListReadDto> getBucketListsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException.UserNonExistsException("해당 사용자가 존재하지 않습니다."));
        List<BucketList> bucketLists = bucketListRepository.findAllByUserId(userId);
        return bucketLists.stream()
                .map(bucketList -> new BucketListDto.BucketListReadDto(bucketList.getId(), bucketList.getGoalText(), bucketList.getCreatedYear()))
                .collect(Collectors.toList());
    }

    // 새 버킷리스트 생성
    public BucketList createBucketList(Long userId, String goalText) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException.UserNonExistsException("해당 사용자가 존재하지 않습니다."));
        BucketList bucketList = new BucketList();
        bucketList.setUser(user);
        bucketList.setGoalText(goalText);
        bucketList.setCreatedYear(LocalDate.now().getYear()); // 현재 연도 설정
        return bucketListRepository.save(bucketList); // 저장 후 반환
    }


    // 특정 ID의 버킷리스트 조회 (유저 ID 포함)
    @Override
    public BucketList getBucketListById(Long userId, Long bucketListId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException.UserNonExistsException("해당 사용자가 존재하지 않습니다."));
        // 특정 ID의 버킷리스트 조회 (유저 ID 포함)
        return bucketListRepository.findByIdAndUserId(bucketListId, userId)
                .orElseThrow(() -> new BucketListException.BucketListNonExistsException(
                        "존재하지 않는 버킷리스트입니다. userId: " + userId + ", bucketListId: " + bucketListId));
    }

    // 버킷리스트 내용 업데이트
    public BucketList updateBucketList(Long userId, Long bucketListId, String goalText) {
        BucketList bucketList = getBucketListById(userId, bucketListId);
        bucketList.setGoalText(goalText);
        return bucketListRepository.save(bucketList);
    }

    // 특정 ID의 버킷리스트 삭제
    public void deleteBucketList(Long bucketListId, Long userId) {
        if (!validateBucketList(userId, bucketListId)) {
            throw new BucketListException.BucketListNoByUserException("사용자가 소유한 버킷리스트가 아닙니다.");
        }
        BucketList bucketList = getBucketListById(bucketListId, userId);
        bucketListRepository.delete(bucketList);
    }
    public boolean validateBucketList(Long userId, Long bucketListId) {
        return bucketListRepository.existsByIdAndUserId(bucketListId, userId);
    }
    @Override
    public Boolean isAchieved(Long bucketListId) {
        BucketList bucketList = bucketListRepository.findById(bucketListId)
                .orElseThrow(() -> new BucketListException.BucketListNonExistsException("존재하지 않는 버킷리스트입니다."));
        return bucketList.getIsAchieved();// null 가능

    }
    @Override
    public void updateAchieveStatus(Long userId, Long bucketListId, Boolean isAchieved) {
        // 버킷리스트 조회
        BucketList bucketList = bucketListRepository.findById(bucketListId)
                .orElseThrow(() -> new BucketListException.BucketListNonExistsException("존재하지 않는 버킷리스트입니다."));

        // 사용자 소유 여부 확인
        if (!validateBucketList(userId, bucketListId)) {
            throw new BucketListException.BucketListNoByUserException("사용자가 소유한 버킷리스트가 아닙니다.");
        }

        // 목표 달성 여부 변경
        bucketList.setIsAchieved(isAchieved);

        // 저장
        bucketListRepository.save(bucketList);
    }


}
