package org.skhuton.fitpete.auth.global.util;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.community.board.domain.Board;
import org.skhuton.fitpete.community.board.domain.repository.BoardRepository;
import org.skhuton.fitpete.community.board.exception.BoardNotFoundException;
import org.skhuton.fitpete.community.comment.domain.Comment;
import org.skhuton.fitpete.community.comment.domain.repository.CommentRepository;
import org.skhuton.fitpete.community.comment.exception.CommentNotFoundException;
import org.skhuton.fitpete.information.domain.Information;
import org.skhuton.fitpete.information.domain.repository.InformationRepository;
import org.skhuton.fitpete.information.exception.InformationNotFoundException;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.springframework.stereotype.Component;

// 엔티티를 조회 공통 기능

@Component
@RequiredArgsConstructor
public class GlobalUtil {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final InformationRepository informationRepository;

    public Board getBoardById(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                () -> new BoardNotFoundException("BoardId " + boardId + " not found"));
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new CommentNotFoundException("CommentId " + commentId + " not found"));
    }

    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Member with email " + email + " not found"));
    }

    public Information getInformationById(Long informationId) {
        return informationRepository.findById(informationId)
                .orElseThrow(() -> new InformationNotFoundException("InforamtionId " + informationId + " not found"));
    }
}
