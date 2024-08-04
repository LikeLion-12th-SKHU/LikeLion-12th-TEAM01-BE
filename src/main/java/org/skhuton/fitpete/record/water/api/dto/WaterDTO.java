package org.skhuton.fitpete.record.water.api.dto;

import lombok.Builder;

@Builder
public record WaterDTO(
        Long waterId,
        Long waterIntake
) {
}
