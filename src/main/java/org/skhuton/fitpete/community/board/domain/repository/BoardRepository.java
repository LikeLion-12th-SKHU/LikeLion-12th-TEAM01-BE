package org.skhuton.fitpete.community.board.domain.repository;

import org.skhuton.fitpete.community.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {
}
