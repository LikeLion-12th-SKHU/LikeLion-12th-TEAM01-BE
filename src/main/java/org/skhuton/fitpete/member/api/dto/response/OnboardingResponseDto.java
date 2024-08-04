package org.skhuton.fitpete.member.api.dto.response;

import lombok.Builder;
import org.skhuton.fitpete.record.goal.domain.Goal;
import org.skhuton.fitpete.record.goal.dto.response.GoalResponseDto;
import org.skhuton.fitpete.record.supplement.domain.Supplement;
import org.skhuton.fitpete.record.supplement.domain.SupplementType;

import java.util.List;

@Builder
public record OnboardingResponseDto(
        String nickname,
        String name,
        int height,
        int weight,
        String gender,
        List<SupplementType> supplementList,
        GoalResponseDto goalResponseDto
) {
}
