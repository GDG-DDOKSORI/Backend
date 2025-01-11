package com.bucketNote.bucketNote.service.bucketList;
import java.util.List;

import com.bucketNote.bucketNote.app.dto.BucketListDto;
import com.bucketNote.bucketNote.domain.entity.BucketList;

public interface BucketListService {
    public List<BucketListDto.BucketListReadDto> getBucketListsByUserId(Long userId);
    public BucketList createBucketList(Long userId, String goalText);
    public BucketList getBucketListById(Long bucketListId, Long userId);
    public BucketList updateBucketList(Long bucketListId, Long userId, String goalText);
    public void deleteBucketList(Long bucketListId, Long userId);
    public boolean validateBucketList(Long userId, Long bucketListId);
}
