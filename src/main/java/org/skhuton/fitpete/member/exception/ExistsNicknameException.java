package org.skhuton.fitpete.member.exception;

import org.skhuton.fitpete.auth.global.error.exception.InvalidException;

public class ExistsNicknameException extends InvalidException {
    public ExistsNicknameException(String nickName) {
        super("이미 사용 중인 닉네임입니다: " + nickName);
    }
}
