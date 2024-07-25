package org.skhuton.fitpete.community.board.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.auth.global.util.GlobalUtil;
import org.skhuton.fitpete.community.board.domain.Board;
import org.skhuton.fitpete.community.board.domain.BoardRecommend;
import org.skhuton.fitpete.community.board.domain.repository.BoardRecommendRepository;
import org.skhuton.fitpete.community.board.exception.ExistsBoardRecommendException;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardRecommendService {
    private final GlobalUtil globalUtil;
    private final BoardRecommendRepository boardRecommendRepository;
    @Transactional
    public void addBoardRecommend(String email, Long boardId) {
        Member member = globalUtil.getMemberByEmail(email);
        Board board = globalUtil.getBoardById(boardId);

        if (boardRecommendRepository.existsByBoardAndMember(board, member)) {
            throw new ExistsBoardRecommendException("이미 추천하는 글입니다.");
        }

        board.updateLikeCount();
        boardRecommendRepository.save(BoardRecommend.builder()
                .board(board)
                .member(member)
                .build());
    }

    @Transactional
    public void cancelBoardRecommend(String email, Long boardId) {
        Member member = globalUtil.getMemberByEmail(email);
        Board board = globalUtil.getBoardById(boardId);

        if (!boardRecommendRepository.existsByBoardAndMember(board, member)) {
            throw new ExistsBoardRecommendException("추천하는 글이 없습니다.");
        }

        BoardRecommend boardRecommend = boardRecommendRepository.findByBoardAndMember(board, member).orElseThrow();

        board.cancelLikeCount();
        boardRecommendRepository.delete(boardRecommend);
    }

}