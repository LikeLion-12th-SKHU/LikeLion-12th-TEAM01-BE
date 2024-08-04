package org.skhuton.fitpete.community.board.exception;

import org.skhuton.fitpete.auth.global.error.exception.AccessDeniedException;

public class NotAuthorizedToEditException extends AccessDeniedException {
    public NotAuthorizedToEditException(String message) {
        super(message);
    }

}
