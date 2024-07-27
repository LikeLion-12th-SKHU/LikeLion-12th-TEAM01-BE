package org.skhuton.fitpete.member.exception;

import org.skhuton.fitpete.auth.global.error.exception.NotFoundException;

public class MemberNotFoundException extends NotFoundException {
    public MemberNotFoundException(String email) {
        super("Member not found with email: " + email);
    }
}