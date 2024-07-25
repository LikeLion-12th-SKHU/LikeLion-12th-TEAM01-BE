package org.skhuton.fitpete.community.board.domain.repository;

import org.skhuton.fitpete.community.board.domain.Board;
import org.skhuton.fitpete.community.board.domain.BoardRecommend;
import org.skhuton.fitpete.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRecommendRepository extends JpaRepository<BoardRecommend, Long> {
    boolean existsByBoardAndMember(Board board, Member member);

    Optional<BoardRecommend> findByBoardAndMember(Board board, Member member);
}