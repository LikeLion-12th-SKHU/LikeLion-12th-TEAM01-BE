package org.skhuton.fitpete.team.api.dto.response;

import org.skhuton.fitpete.team.domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record TeamListResponseDto(
        List<TeamInfoResponseDto> team // 팀 정보 리스트
) {
    public static TeamListResponseDto fromEntities(List<Team> team) {
        if (team == null) {
            team = new ArrayList<>(); // 빈 리스트로 초기화
        }

        List<TeamInfoResponseDto> teamInfoDtoList = team.stream()
                .map(TeamInfoResponseDto::fromEntity) // 각 Team을 TeamInfoResponseDto로 변환
                .collect(Collectors.toList());
        return new TeamListResponseDto(teamInfoDtoList);
    }
}
