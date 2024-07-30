package org.skhuton.fitpete.member.api.dto.request;

import org.skhuton.fitpete.member.domain.Member;

public record MemberSaveRequestDto(
        String nickname,
        String name,
        String email,
        int height,
        int weight,
        String gender
) {
    public Member toEntity() {
        return Member.builder()
                .nickname(nickname)
                .name(name)
                .email(email)
                .height(height)
                .weight(weight)
                .gender(gender)
                .firstLogin(true)
                .build();
    }
}
