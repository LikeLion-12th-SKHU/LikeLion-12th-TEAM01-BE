package org.skhuton.fitpete.auth.global.error.dto;

public record ErrorResponseDTO(
        int statusCode,
        String message
) {
}