package org.skhuton.fitpete.member.api.dto.request;

import lombok.Builder;

@Builder
public record OnboardingInfoUpdateRequestDto(
        String nickname,
        String name,
        int height,
        int weight,
        String gender
) {
}
