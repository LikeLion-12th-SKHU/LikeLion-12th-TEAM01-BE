package org.skhuton.fitpete.member.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.skhuton.fitpete.member.application.MemberService;
import org.skhuton.fitpete.auth.global.template.ResponseTemplate;
import org.skhuton.fitpete.member.api.dto.request.OnboardingInfoUpdateRequestDto;
import org.skhuton.fitpete.member.api.dto.response.MemberInfoResponseDto;
import org.skhuton.fitpete.member.exception.ExistsNicknameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary = "최초 로그인 구별", description = "최초 로그인 여부 확인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/success")
    public ResponseTemplate<Boolean> isFirstLogin(@AuthenticationPrincipal String email) {
        return new ResponseTemplate<>(
                HttpStatus.OK,
                "최초 로그인 여부",
                memberService.memberFirstLogin(email));
    }

    @Operation(summary = "닉네임 중복 검사", description = "닉네임 중복 검사")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용 가능한 닉네임 !"),
            @ApiResponse(responseCode = "400", description = "중복된 닉네임"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/nickname")
    public ResponseEntity<ResponseTemplate<Boolean>> duplicateNickName(@RequestParam(name = "nickname") String nickName) {
        try {
            memberService.validateDuplicateNickName(nickName);
            ResponseTemplate<Boolean> response = new ResponseTemplate<>(HttpStatus.OK, "사용 가능한 닉네임입니다.", true);
            return ResponseEntity.ok(response);
        } catch (ExistsNicknameException e) {
            ResponseTemplate<Boolean> response = new ResponseTemplate<>(HttpStatus.BAD_REQUEST, e.getMessage(), false);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Operation(summary = "전체 사용자 랭킹 조회", description = "전체 사용자들의 랭킹을 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "전체 사용자 랭킹 조회 성공!"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/rank")
    public ResponseEntity<ResponseTemplate<List<MemberInfoResponseDto>>> getAllMembersRank() {
        List<MemberInfoResponseDto> responseDtos = memberService.getAllMembersRank().stream()
                .map(MemberInfoResponseDto::fromEntity) // Member를 MemberInfoDto로 변환
                .toList();

        ResponseTemplate<List<MemberInfoResponseDto>> response = new ResponseTemplate<>(
                HttpStatus.OK,
                "전체 사용자 랭킹을 성공적으로 조회하였습니다.",
                responseDtos
        );

        return ResponseEntity.ok(response);
    }
}
