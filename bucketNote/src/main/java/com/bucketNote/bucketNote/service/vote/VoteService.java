package com.bucketNote.bucketNote.service.vote;

public interface VoteService {
    public void voteOnBucketList(Long userId, Long bucketListId, Boolean isPossible);
}
