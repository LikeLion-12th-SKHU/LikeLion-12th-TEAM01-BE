package org.skhuton.fitpete.record.exercise.api.dto;

import lombok.Builder;

@Builder
public record ExerciseListDTO(
        Long exercisreId,
        String exerciseName,
        int exerciseDuration
) {
}
