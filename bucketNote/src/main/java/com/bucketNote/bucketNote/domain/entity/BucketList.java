package com.bucketNote.bucketNote.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bucket_list")
@Getter
@Setter
@NoArgsConstructor
public class BucketList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // User와 연관 관계

    @Column(nullable = false)
    private String goalText; // 버킷리스트 내용

    @Column(nullable = false, updatable = false, columnDefinition = "YEAR")
    private Integer createdYear = LocalDate.now().getYear(); // 현재 연도만 저장

}

