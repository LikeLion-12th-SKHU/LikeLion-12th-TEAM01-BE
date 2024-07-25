package org.skhuton.fitpete.community.board.domain.repository;

import org.skhuton.fitpete.community.board.api.dto.response.BoardInfoResponseDTO;
import org.skhuton.fitpete.community.board.domain.Board;
import org.skhuton.fitpete.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardCustomRepository {
    Page<BoardInfoResponseDTO> findByCategoryWithBoard(Member member, String category, Pageable pageable);

    Page<BoardInfoResponseDTO> findByBoardAll(Member member, Pageable pageable);

    Board findByDetailBoard(Board board);
}
