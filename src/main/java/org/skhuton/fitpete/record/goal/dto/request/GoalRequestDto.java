package org.skhuton.fitpete.record.goal.dto.request;

public record GoalRequestDto(
        int goalWeight,
        int goalWater,
        int goalExerciseDuration,
        int goalExerciseRobbery,
        int goalSleep
) {
}
