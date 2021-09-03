package com.community.spring_boot_community.domain;

import com.community.spring_boot_community.domain.enums.BoardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Board {
    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE) for junit5
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String title;

    @Column
    private String subTitle;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    //lazy->객체가 실제로 사용될 때 조회
    //User 테이블의 기본키인 idx로 값이 저장됨
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

}
