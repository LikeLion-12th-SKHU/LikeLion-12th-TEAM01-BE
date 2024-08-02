package org.skhuton.fitpete.record.diet.api.dto;

import lombok.Builder;

@Builder
public record DietDTO(
        String foodDescription,
        String photoUrl
) {
}
