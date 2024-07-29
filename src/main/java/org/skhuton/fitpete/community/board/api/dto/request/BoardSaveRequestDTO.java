package org.skhuton.fitpete.community.board.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.skhuton.fitpete.community.board.domain.Board;
import org.skhuton.fitpete.community.board.domain.Category;
import org.skhuton.fitpete.member.domain.Member;

import java.util.List;

public record BoardSaveRequestDTO(
        Category category,
        @NotBlank(message = "제목은 필수 입력 항목입니다.")
        String title,
        String content,
        String createDate,
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
