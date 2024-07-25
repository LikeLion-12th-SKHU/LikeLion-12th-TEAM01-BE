package org.skhuton.fitpete.community.board.api.dto.request;

import org.skhuton.fitpete.community.board.domain.Category;

import java.util.List;

public record BoardUpdateRequestDTO(
        Category category,
        String title,
        String content,
        int ModificationDate,
        List<String> newImageUrl
) {
}
