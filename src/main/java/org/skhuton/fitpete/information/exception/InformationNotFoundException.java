package org.skhuton.fitpete.information.exception;

import org.skhuton.fitpete.auth.global.error.exception.NotFoundException;

public class InformationNotFoundException extends NotFoundException {
    public InformationNotFoundException(String message) { super("Information not found"); }
}
