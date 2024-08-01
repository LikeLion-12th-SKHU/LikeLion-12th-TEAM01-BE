package org.skhuton.fitpete.record.sleep.api.dto;

import lombok.Builder;

@Builder
public record SleepDTO(
        Double sleepHours
) {
}
