package org.skhuton.fitpete.record.supplement.api.dto;

import lombok.Builder;

@Builder
public record SupplementTypeDTO(
        Long supplementTypeId,
        String name
) {
}
