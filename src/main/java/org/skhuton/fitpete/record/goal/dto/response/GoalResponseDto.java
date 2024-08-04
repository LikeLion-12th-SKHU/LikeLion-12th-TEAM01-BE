package org.skhuton.fitpete.record.goal.dto.response;

import lombok.Builder;
import org.skhuton.fitpete.record.goal.domain.Goal;

@Builder
public record GoalResponseDto(
        int goalWeight,
        int goalWater,
        int goalExerciseDuration,
        int goalExerciseRobbery,
        int goalSleep
) {

    public static GoalResponseDto from(Goal goal) {
        return GoalResponseDto.builder()
                .goalWeight(goal.getGoalWeigh())
                .goalWater(goal.getGoalWater())
                .goalExerciseDuration(goal.getGoalExerciseDuration())
                .goalExerciseRobbery(goal.getGoalExerciseRobbery())
                .goalSleep(goal.getGoalSleep())
                .build();
    }
}
