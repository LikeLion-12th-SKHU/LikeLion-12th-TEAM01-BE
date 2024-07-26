package org.skhuton.fitpete.community.board.domain.repository;

import org.skhuton.fitpete.community.board.domain.Board;
import org.skhuton.fitpete.community.board.domain.BoardReport;
import org.skhuton.fitpete.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardReportRepository extends JpaRepository<BoardReport, Long> {
    boolean existsByBoardAndMember(Board board, Member member);
}
