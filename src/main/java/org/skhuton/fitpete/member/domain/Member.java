package org.skhuton.fitpete.member.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.skhuton.fitpete.member.api.dto.request.OnboardingInfoUpdateRequestDto;
import org.skhuton.fitpete.team.domain.Team;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    @Schema(description = "사용자 id", example = "1")
    private Long memberId;

    @Schema(description = "닉네임", example = "길동이")
    private String nickname;

    @Schema(description = "사용자 이름", example = "홍길동")
    private String name;

    @Schema(description = "이메일", example = "abc@gmail.com")
    private String email;

    @Schema(description = "키", example = "160")
    private int height;

    @Schema(description = "몸무게", example = "50")
    private int weight;

    @Schema(description = "성별", example = "여자, 남자")
    private String gender;

    @Schema(description = "최초 로그인", example = "true, false")
    private boolean firstLogin;

    @Enumerated(EnumType.STRING)
    @Schema(description = "권한", example = "ROLE_USER")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    @Schema(description = "팀")
    private Team team;

    @Builder
    public Member(String nickname, String name, String email, int height, int weight, String gender, Role role, Team team) {
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.role = role;
        this.team = team;
    }

    public void firstLongUpdate() {
        this.firstLogin = false;
    }

    public void onboardingUpdate(OnboardingInfoUpdateRequestDto onboardingInfoUpdateRequestDto) {
        this.nickname = onboardingInfoUpdateRequestDto.nickname();
        this.name = onboardingInfoUpdateRequestDto.name();
        this.height = onboardingInfoUpdateRequestDto.height();
        this.weight = onboardingInfoUpdateRequestDto.weight();
        this.gender = onboardingInfoUpdateRequestDto.gender();
    }

    public OnboardingInfoUpdateRequestDto toDto() {
        return OnboardingInfoUpdateRequestDto.builder()
                .nickname(nickname)
                .name(name)
                .height(height)
                .weight(weight)
                .gender(gender)
                .build();
    }

}
