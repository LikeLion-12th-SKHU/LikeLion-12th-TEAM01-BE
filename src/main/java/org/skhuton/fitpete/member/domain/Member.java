package org.skhuton.fitpete.member.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MEMBER_NICKNAME")
    private String nickname; // 닉네임

    @Column(name = "MEMBER_NAME")
    private String name; // 사용자 이름

    @Column(name = "MEMBER_EMAIL")
    private String email; // 이메일

    @Column(name = "MEMBER_HEIGHT")
    private int height; // 키

    @Column(name = "MEMBER_WEIGHT")
    private int weight; // 몸무게

    @Column(name = "MEMBER_SEX")
    private String sex; // 성별

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false)
    private Role role;
}
