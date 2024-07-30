package org.skhuton.fitpete.member.api.dto.response;

import org.skhuton.fitpete.member.domain.Member;

public record MemberInfoResponseDto(
        Long memberId,
        String nickname,
        String name,
        String email
) {
    public static MemberInfoResponseDto fromEntity(Member member) {
        return new MemberInfoResponseDto(
                member.getMemberId(),
                member.getNickname(),
                member.getName(),
                member.getEmail()
        );
    }
}
