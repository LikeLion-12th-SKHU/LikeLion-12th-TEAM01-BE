package org.skhuton.fitpete.information.api.dto.response;

import org.skhuton.fitpete.information.domain.Information;

public record InformationRecommendInfoResponseDto(
        Long informationRecommendId,
        Information information
) {
    public InformationRecommendInfoResponseDto(Long informationRecommendId, Information information) {
        this.informationRecommendId = informationRecommendId;
        this.information = information;
    }
}
