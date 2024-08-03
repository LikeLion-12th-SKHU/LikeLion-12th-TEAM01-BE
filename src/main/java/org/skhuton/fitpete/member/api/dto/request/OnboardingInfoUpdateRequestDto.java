package org.skhuton.fitpete.member.api.dto.request;

import lombok.Builder;
import org.skhuton.fitpete.record.goal.domain.Goal;

import java.util.List;

@Builder
public record OnboardingInfoUpdateRequestDto(
        String nickname,
        String name,
        int height,
        int weight,
        String gender,
        List<Goal>  goal
) {
}
