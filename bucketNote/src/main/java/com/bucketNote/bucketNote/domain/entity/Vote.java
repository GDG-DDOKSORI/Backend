package com.bucketNote.bucketNote.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bucketlist_id", nullable = false)
    private BucketList bucketList; // 투표 대상 버킷리스트

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 투표한 사용자

    @Column(nullable = false)
    private Boolean isPossible; // 가능 여부 (true: 가능, false: 불가능)
}

