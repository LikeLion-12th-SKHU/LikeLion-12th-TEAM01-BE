package org.skhuton.fitpete.record.weight.api.dto;

import java.time.LocalDateTime;

public record WeightDTO(
        Long weightId,
        Double weight,
        LocalDateTime recordedAt
) {
}