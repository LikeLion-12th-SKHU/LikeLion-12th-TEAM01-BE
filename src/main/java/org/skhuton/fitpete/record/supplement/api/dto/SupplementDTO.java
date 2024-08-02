package org.skhuton.fitpete.record.supplement.api.dto;

import lombok.Builder;

@Builder
public record SupplementDTO(
        SupplementTypeDTO supplementType,
        Boolean tookSupplements
) {
}
