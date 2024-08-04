package org.skhuton.fitpete.community.board.api.dto.response;

import lombok.Builder;
import org.springframework.data.domain.Page;

@Builder
public record BoardListResponseDTO(
        Page<BoardInfoResponseDTO> boardInfoResponseDTOs
) {
    public static BoardListResponseDTO from(Page<BoardInfoResponseDTO> boardInfoResponseDTO) {
        return BoardListResponseDTO.builder()
                .boardInfoResponseDTOs(boardInfoResponseDTO)
                .build();
    }

}