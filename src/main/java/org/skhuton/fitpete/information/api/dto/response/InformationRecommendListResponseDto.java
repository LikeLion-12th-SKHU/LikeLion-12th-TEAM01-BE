package org.skhuton.fitpete.information.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record InformationRecommendListResponseDto(
        List<InformationRecommendInfoResponseDto> informationRecommendInfoResponseDtos
) {
    public static InformationRecommendListResponseDto from(List<InformationRecommendInfoResponseDto> informationRecommendInfoResponseDtos) {
        return InformationRecommendListResponseDto.builder()
                .informationRecommendInfoResponseDtos(informationRecommendInfoResponseDtos)
                .build();
    }
}