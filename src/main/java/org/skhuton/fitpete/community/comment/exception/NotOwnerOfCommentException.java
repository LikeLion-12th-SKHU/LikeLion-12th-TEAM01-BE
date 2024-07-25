package org.skhuton.fitpete.community.comment.exception;

import org.skhuton.fitpete.auth.global.error.exception.AccessDeniedException;

public class NotOwnerOfCommentException extends AccessDeniedException {
    public NotOwnerOfCommentException(String message) {
        super(message);
    }
}
