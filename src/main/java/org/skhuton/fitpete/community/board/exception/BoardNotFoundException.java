package org.skhuton.fitpete.community.board.exception;

import org.skhuton.fitpete.auth.global.error.exception.NotFoundException;

public class BoardNotFoundException extends NotFoundException {

    public BoardNotFoundException(String message) {
        super(message);
    }

    public BoardNotFoundException() {
        this("존재하지 않는 커뮤니티 글 입니다.");
    }
}
