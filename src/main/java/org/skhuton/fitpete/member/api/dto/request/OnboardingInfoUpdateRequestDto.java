package org.skhuton.fitpete.member.dto;

import lombok.Builder;

@Builder
public record OnboardingInfoUpdateRequestDto(
        String nickname,
        String name,
        int height,
        int weight,
        String sex
) {
}
