package org.skhuton.fitpete.community.comment.api.dto.request;

import org.skhuton.fitpete.community.board.domain.Board;
import org.skhuton.fitpete.community.comment.domain.Comment;
import org.skhuton.fitpete.member.domain.Member;

public record CommentSaveRequestDTO(
        Long boardId,
        String content
) {
    public Comment toEntity(Member member, Board board) {
        return Comment.builder()
                .content(content)
                .writer(member)
                .board(board)
                .build();
    }
}
