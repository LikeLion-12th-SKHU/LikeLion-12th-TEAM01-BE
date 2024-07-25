package org.skhuton.fitpete.community.board.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.community.board.api.dto.response.BoardInfoResponseDTO;
import org.skhuton.fitpete.community.board.domain.Board;
import org.skhuton.fitpete.community.board.domain.Category;
import org.skhuton.fitpete.community.board.domain.QBoard;
import org.skhuton.fitpete.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.skhuton.fitpete.community.board.domain.QBoard.board;
import static org.skhuton.fitpete.community.comment.domain.QComment.comment;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardCustomRepositoryImplement implements BoardCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BoardInfoResponseDTO> findByCategoryWithBoard(Member member, String category, Pageable pageable) {
        long total = queryFactory
                .selectFrom(board)
                .where(board.category.eq(Category.valueOf(category)))
                .fetchCount();

        List<Board> boards = queryFactory
                .selectFrom(board)
                .where(board.category.eq(Category.valueOf(category)))
                .orderBy(board.boardDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<BoardInfoResponseDTO> boardDTOs = boards.stream()
                .map(b -> BoardInfoResponseDTO.of(member, b))
                .toList();

        return new PageImpl<>(boardDTOs, pageable, total);
    }

    @Override
    public Page<BoardInfoResponseDTO> findByBoardAll(Member member, Pageable pageable) {
        long total = queryFactory
                .selectFrom(board)
                .fetchCount();

        List<Board> boards = queryFactory
                .selectFrom(board)
                .orderBy(board.boardDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<BoardInfoResponseDTO> boardDTOs = boards.stream()
                .map(b -> BoardInfoResponseDTO.of(member, b))
                .toList();

        return new PageImpl<>(boardDTOs, pageable, total);
    }

    @Override
    public Board findByDetailBoard(Board board) {
        QBoard qBoard = QBoard.board;
        return queryFactory
                .selectFrom(qBoard)
                .leftJoin(qBoard.comments).fetchJoin()
                .where(qBoard.boardId.eq(board.getBoardId()))
                .fetchOne();

    }
}