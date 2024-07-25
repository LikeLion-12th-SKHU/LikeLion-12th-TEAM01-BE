package org.skhuton.fitpete.community.board.api.dto.request;

import org.skhuton.fitpete.community.board.domain.Board;
import org.skhuton.fitpete.community.board.domain.Category;
import org.skhuton.fitpete.member.domain.Member;

import java.util.List;

public record BoardSaveRequestDTO(
        Category category,
        String title,
        String content,
        int createDate,
        List<String> imageUrl
) {
    public Board toEntity(Member member) {
        return Board.builder()
                .category(category)
                .title(title)
                .content(content)
                .writer(member)
                .build();
    }
}
