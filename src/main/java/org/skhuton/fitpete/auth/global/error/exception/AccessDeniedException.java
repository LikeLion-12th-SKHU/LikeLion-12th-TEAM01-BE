package org.skhuton.fitpete.auth.global.error.exception;

// 접근 거부 예외 처리
public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String message) {

        super(message);
    }
}