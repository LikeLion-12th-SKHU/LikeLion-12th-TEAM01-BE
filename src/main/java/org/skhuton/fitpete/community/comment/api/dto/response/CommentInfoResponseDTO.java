package org.skhuton.fitpete.community.comment.api.dto.response;

import lombok.Builder;
import org.skhuton.fitpete.community.comment.domain.Comment;

@Builder
public record CommentInfoResponseDTO(
        Long writerMemberId,
        Long commentId,
        String content
) {
    public static CommentInfoResponseDTO of(Comment comment) {
        return CommentInfoResponseDTO.builder()
                .writerMemberId(comment.getWriter().getMemberId())
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .build();
    }

}