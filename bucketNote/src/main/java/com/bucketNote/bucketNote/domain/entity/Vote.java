package com.bucketNote.bucketNote.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long bucketListId; // 투표 대상 버킷리스트 ID

    @Column(nullable = false)
    private Long voterId; // 투표를 한 사용자 ID

    @Column(nullable = false)
    private Long receiverId; // 투표를 받은 사용자 ID

    @Column(nullable = false)
    private Boolean isPossible; // 가능 여부 (true: 가능, false: 불가능)
}

