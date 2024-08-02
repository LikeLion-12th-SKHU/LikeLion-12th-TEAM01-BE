package org.skhuton.fitpete.information.api.dto.response;

import org.skhuton.fitpete.information.domain.Information;

public record InformationStoreInfoResponseDto(
        Long informationStoreId,
        Information information
) {
    public InformationStoreInfoResponseDto(Long informationStoreId, Information information) {
        this.informationStoreId = informationStoreId;
        this.information = information;
    }
}
