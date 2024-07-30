package org.skhuton.fitpete.information.api.dto.response;

import org.skhuton.fitpete.community.board.domain.Category;
import org.skhuton.fitpete.information.domain.Information;

public record InformationInfoResponseDto(
        Long informationId,
        String title,
        String content,
        String createdAt,
        String createdBy,
        String modifiedAt,
        Long recommendCount,
        Long viewCount,
        Category category
) {
    public static InformationInfoResponseDto from(Information information) {
        return new InformationInfoResponseDto(
                information.getInformationId(),
                information.getTitle(),
                information.getContent(),
                information.getCreatedAt(),
                information.getCreatedBy(),
                information.getModifiedAt(),
                information.getRecommendCount(),
                information.getViewCount(),
                information.getCategory()
        );
    }
}
