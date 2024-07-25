package org.skhuton.fitpete.community.comment.exception;

import org.skhuton.fitpete.auth.global.error.exception.NotFoundException;

public class CommentNotFoundException extends NotFoundException {
    public CommentNotFoundException(String message) {
        super(message);
    }

    public CommentNotFoundException() {
        this("존재하지 않는 댓글 입니다.");
    }
}