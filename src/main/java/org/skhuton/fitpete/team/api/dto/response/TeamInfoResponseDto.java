package org.skhuton.fitpete.team.api.dto.response;

import org.skhuton.fitpete.member.api.dto.response.MemberInfoResponseDto;
import org.skhuton.fitpete.team.domain.Team;

import java.util.List;
import java.util.stream.Collectors;

public record TeamInfoResponseDto(
        Long teamId,
        String teamName,
        List<MemberInfoResponseDto> members // 팀원 정보를 담기 위한 DTO 리스트
) {
    public static TeamInfoResponseDto fromEntity(Team team) {
        return new TeamInfoResponseDto(
                team.getTeamId(),
                team.getTeamName(),
                team.getMembers().stream()
                        .map(MemberInfoResponseDto::fromEntity) // Member를 MemberInfoDto로 변환
                        .collect(Collectors.toList())
        );
    }
}
