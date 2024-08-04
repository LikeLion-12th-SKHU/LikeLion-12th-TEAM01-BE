package org.skhuton.fitpete.member.exception;

import org.skhuton.fitpete.auth.global.error.exception.NotFoundException;

public class MemberNotFoundException extends NotFoundException {
    public MemberNotFoundException(String email) {
        super("사용자를 찾을 수 없습니다.: " + email);
    }
}