package org.skhuton.fitpete.information.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record InformationStoreListResponseDto(
        List<InformationStoreInfoResponseDto> informationStoreListResponseDtos
) {
    public static InformationStoreListResponseDto from(List<InformationStoreInfoResponseDto> infoResponseDtos) {
        return InformationStoreListResponseDto.builder()
                .informationStoreListResponseDtos(infoResponseDtos)
                .build();
    }
}
