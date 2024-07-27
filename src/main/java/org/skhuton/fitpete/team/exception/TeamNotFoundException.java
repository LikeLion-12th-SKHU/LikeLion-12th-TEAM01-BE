package org.skhuton.fitpete.team.exception;

import org.skhuton.fitpete.auth.global.error.exception.NotFoundException;

public class TeamNotFoundException extends NotFoundException {
    public TeamNotFoundException(Long teamId) { super("Team not found: " + teamId); }
}
