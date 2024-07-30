package org.skhuton.fitpete.team.api.dto.request;

import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.team.domain.Team;

import java.util.Collections;

public record TeamSaveRequestDto(
        String teamName
) {
    public Team toEntity(Member member) {
        return Team.builder()
                .teamName(teamName)
                .members(Collections.singletonList(member))
                .build();
    }
}
