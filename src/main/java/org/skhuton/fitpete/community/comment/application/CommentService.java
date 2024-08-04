package org.skhuton.fitpete.community.comment.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.auth.global.util.GlobalUtil;
import org.skhuton.fitpete.community.board.domain.Board;
import org.skhuton.fitpete.community.comment.api.dto.request.CommentSaveRequestDTO;
import org.skhuton.fitpete.community.comment.api.dto.request.CommentUpdateRequestDTO;
import org.skhuton.fitpete.community.comment.api.dto.response.CommentInfoResponseDTO;
import org.skhuton.fitpete.community.comment.domain.Comment;
import org.skhuton.fitpete.community.comment.domain.repository.CommentRepository;
import org.skhuton.fitpete.community.comment.exception.NotOwnerOfCommentException;
import org.skhuton.fitpete.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final GlobalUtil globalUtil;
    private final CommentRepository commentRepository;

    // 댓글 저장
    public void commentSave(String email, CommentSaveRequestDTO commentSaveRequestDTO) {
        Member member = globalUtil.getMemberByEmail(email);
        Board board = globalUtil.getBoardById(commentSaveRequestDTO.boardId());

        commentRepository.save(commentSaveRequestDTO.toEntity(member, board));
    }

    // 댓글 수정
    @Transactional
    public CommentInfoResponseDTO commentUpdate(String email, Long commentId, CommentUpdateRequestDTO commentUpdateRequestDTO) {
        Member member = globalUtil.getMemberByEmail(email);
        Comment comment = globalUtil.getCommentById(commentId);

        checkCommentOwnership(member, comment);

        comment.updateContent(commentUpdateRequestDTO.content());

        return CommentInfoResponseDTO.of(comment);
    }

    // 댓글 삭제
    @Transactional
    public void commentDelete(String email, Long commentId) {
        Member member = globalUtil.getMemberByEmail(email);
        Comment comment = globalUtil.getCommentById(commentId);

        checkCommentOwnership(member, comment);

        commentRepository.delete(comment);
    }

    private static void checkCommentOwnership(Member member, Comment comment) {
        if (!member.getMemberId().equals(comment.getWriter().getMemberId())) {
            throw new NotOwnerOfCommentException("댓글 작성자만 수정 또는 삭제가 가능합니다.");
        }
    }
}