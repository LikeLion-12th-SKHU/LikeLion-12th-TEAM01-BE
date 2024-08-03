package org.skhuton.fitpete.member.api.dto.request;

import lombok.Builder;
import org.skhuton.fitpete.record.goal.domain.Goal;

@Builder
public record OnboardingInfoUpdateRequestDto(
        String nickname,
        String name,
        int height,
        int weight,
        String gender,
        Goal goal
) {
}
