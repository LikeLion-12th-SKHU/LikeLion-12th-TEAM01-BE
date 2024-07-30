package org.skhuton.fitpete.information.api.dto.response;

import lombok.Builder;
import org.springframework.data.domain.Page;

@Builder
public record InformationListResponseDto (
        Page<InformationInfoResponseDto> informationInfoResponseDtos
) {
    public static InformationListResponseDto from(Page<InformationInfoResponseDto> informationInfoResponseDtos) {
        return InformationListResponseDto.builder()
                .informationInfoResponseDtos(informationInfoResponseDtos)
                .build();
    }
}
