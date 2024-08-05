package org.skhuton.fitpete.member.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.skhuton.fitpete.member.api.dto.request.OnboardingInfoUpdateRequestDto;
import org.skhuton.fitpete.member.api.dto.response.OnboardingResponseDto;
import org.skhuton.fitpete.record.goal.domain.Goal;
import org.skhuton.fitpete.record.goal.dto.response.GoalResponseDto;
import org.skhuton.fitpete.record.supplement.domain.Supplement;
import org.skhuton.fitpete.team.domain.Team;

import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    @Schema(description = "레벨 카운터", example = "1")
    private int levelCount = 0;

    @Schema(description = "캐릭터 레벨", example = "1")
    private int level;

    @Schema(description = "최초 로그인", example = "true, false")
    private boolean firstLogin;

    @Enumerated(EnumType.STRING)
    @Schema(description = "권한", example = "ROLE_USER")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    @Schema(description = "팀")
    private Team team;

    @Schema(description = "영양제리스트")
    private String supplementList;

    @OneToOne(mappedBy = "member")
    private Goal goal;

    @Builder
    public Member(String nickname, String name, String email, Role role, int level) {
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.role = role;
        this.level = level;
    }

    public void firstLongUpdate() {
        this.firstLogin = false;
    }

    public void onboardingUpdate(OnboardingInfoUpdateRequestDto onboardingInfoUpdateRequestDto, Goal goal) {
        this.nickname = onboardingInfoUpdateRequestDto.nickname();
        this.name = onboardingInfoUpdateRequestDto.name();
        this.height = onboardingInfoUpdateRequestDto.height();
        this.weight = onboardingInfoUpdateRequestDto.weight();
        this.gender = onboardingInfoUpdateRequestDto.gender();
        this.supplementList = onboardingInfoUpdateRequestDto.supplementList();
        this.goal = goal;
    }

    public OnboardingResponseDto.OnboardingResponseDtoBuilder toDto() {
        return OnboardingResponseDto.builder()
                .nickname(nickname)
                .name(name)
                .height(height)
                .weight(weight)
                .gender(gender)
                .goalResponseDto(GoalResponseDto.from(goal));
    }

    public boolean isDeveloper() {
        return this.role.equals("ROLE_ADMIN");
    }

    public void incrementLevelCount() { this.levelCount++; }
    public void cancelLevelCount() {
        if (this.levelCount <= 0)
            this.levelCount = 0;
        else
            this.levelCount--;}

    public void checkLevelUp() {
        if (this.levelCount < 7) {
            this.level = 1;
        }
    }

}
