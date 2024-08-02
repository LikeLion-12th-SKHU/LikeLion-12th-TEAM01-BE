package org.skhuton.fitpete.record.exercise.api.dto;

import lombok.Builder;

@Builder
public record ExerciseListDTO(
        Long id,
        String exerciseName,
        int exerciseDuration
) {
}
