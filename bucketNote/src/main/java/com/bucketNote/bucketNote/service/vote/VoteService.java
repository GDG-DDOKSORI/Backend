package com.bucketNote.bucketNote.service.vote;

import java.util.List;
import java.util.Map;

public interface VoteService {
    //public void voteOnBucketList(Long userId, Long bucketListId, Boolean isPossible);

    //void addVote(Long voterId, Long receiverId, Long bucketListId);
    public void voteOnBucketList(Long bucketListId, Long voterId, Boolean isPossible);
    Map<String, Long> getVoteResults(Long bucketListId);
    public List<String> getVoterNamesByBucketListIdAndIsPossible(Long bucketListId, boolean isPossible);
}
