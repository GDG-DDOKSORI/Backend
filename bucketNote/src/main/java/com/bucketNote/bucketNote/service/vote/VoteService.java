package com.bucketNote.bucketNote.service.vote;

import java.util.Map;

public interface VoteService {
    //public void voteOnBucketList(Long userId, Long bucketListId, Boolean isPossible);

    //void addVote(Long voterId, Long receiverId, Long bucketListId);
    public void voteOnBucketList(Long bucketListId, Long voterId, Long receiverId, Boolean isPossible);
    Map<String, Long> getVoteResults(Long bucketListId);
}
