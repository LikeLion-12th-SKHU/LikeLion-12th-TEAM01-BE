package org.skhuton.fitpete.record.menstrual.api.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MenstrualDTO(
        Boolean menstrualCycle,
        LocalDate menstrualStartDate,
        LocalDate menstrualEndDate
) {
}