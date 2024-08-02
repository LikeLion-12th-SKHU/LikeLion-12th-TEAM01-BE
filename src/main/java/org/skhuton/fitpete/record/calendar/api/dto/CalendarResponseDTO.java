package org.skhuton.fitpete.record.calendar.api.dto;

import lombok.Builder;
import org.skhuton.fitpete.record.exercise.api.dto.ExerciseListDTO;

@Builder
public record CalendarResponseDTO(
        ExerciseListDTO exerciseList
) {
}
