package org.skhuton.fitpete.team.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.skhuton.fitpete.auth.global.template.ResponseTemplate;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.team.application.TeamService;
import org.skhuton.fitpete.team.domain.Team;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    public TeamController(TeamService teamService) { this.teamService = teamService; }

    @Operation(summary = "팀 등록", description = "팀 등록 및 초기 멤버 설정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 등록 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @PostMapping
    public ResponseEntity<ResponseTemplate<Team>> createTeam(@RequestBody Team team) {

        Team savedTeam = teamService.createTeam(team);
        ResponseTemplate<Team> response = new ResponseTemplate<>(
                HttpStatus.CREATED,
                "팀이 성공적으로 생성되었습니다.",
                savedTeam
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "팀 멤버 추가", description = "팀에 멤버 추가")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 멤버 추가 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @PostMapping("/{teamId}/members")
    public ResponseEntity<ResponseTemplate<Member>> addMember(@PathVariable Long teamId, @RequestBody Member member) {
        Member savedMember = teamService.addMember(teamId, member);
        ResponseTemplate<Member> response = new ResponseTemplate<>(
                HttpStatus.CREATED,
                "멤버가 성공적으로 추가되었습니다.",
                savedMember
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "팀 조회", description = "멤버의 팀 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 조회 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/members/{memberId}/teams")
    public ResponseEntity<ResponseTemplate<List<Team>>> getTeamsByMemberId(@PathVariable Long memberId) {
        List<Team> teams = teamService.getTeamsByMemberId(memberId);
        ResponseTemplate<List<Team>> response = new ResponseTemplate<>(
                HttpStatus.OK,
                "멤버의 팀 목록을 성공적으로 조회 하였습니다.",
                teams
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "팀 삭제", description = "팀 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 삭제 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패" ,content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @DeleteMapping("/members/{memberId}")
    public ResponseEntity<ResponseTemplate<Void>> deleteMember(@PathVariable Long memberId) {
        teamService.removeMember(memberId);
        ResponseTemplate<Void> response = new ResponseTemplate<>(
                HttpStatus.OK,
                "멤버가 성공적으로 삭제되었습니다."
        );
        return ResponseEntity.ok(response);
    }

}
