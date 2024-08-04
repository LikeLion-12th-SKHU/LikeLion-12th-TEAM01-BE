package org.skhuton.fitpete.information.api.dto.request;

import lombok.Builder;
import org.skhuton.fitpete.community.board.domain.Category;

public record InformationSaveRequestDto(
        String title,
        String content,
        String createdAt,
        String createdBy,
        String modifiedAt,
        Long recommendCount,
        Long storeCount,
        Long viewCount,
        Category category

) {
    @Builder
    public InformationSaveRequestDto(String title, String content, String createdAt, String createdBy, String modifiedAt,
                                     Long recommendCount, Long storeCount, Long viewCount, Category category) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.recommendCount = recommendCount;
        this.storeCount = storeCount;
        this.viewCount = viewCount;
        this.category = category;
    }
}
