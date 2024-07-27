package org.skhuton.fitpete.member.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.skhuton.fitpete.auth.global.template.ResponseTemplate;
import org.skhuton.fitpete.member.application.MemberService;
import org.skhuton.fitpete.member.application.MyPageService;
import org.skhuton.fitpete.member.dto.OnboardingInfoUpdateRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mypage")
public class MyPageController {

    private final MyPageService myPageService;
    public MyPageController(MyPageService myPageService) { this.myPageService = myPageService; }

    @Operation(summary = "온보딩 정보 업데이트", description = "로그인 후 온보딩 정보 업데이트(유저 정보 수정)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "온보딩 정보 업데이트 성공 !"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 값"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @PostMapping("/onboarding")
    public ResponseTemplate<String> onboardingInfoUpdate(@AuthenticationPrincipal String email,
                                                         @RequestBody OnboardingInfoUpdateRequestDto onboardingInfoUpdateRequestDto) {
        myPageService.onboardingInfoUpdate(email, onboardingInfoUpdateRequestDto);
        return new ResponseTemplate<>(HttpStatus.OK, "온보딩 정보가 성공적으로 업데이트되었습니다.", "성공");
    }

    // 로그아웃
    //@PostMapping("/logout")

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @DeleteMapping("/{memberId}")
    public ResponseTemplate<String> memberDeleteAccount(@AuthenticationPrincipal String email) {
        myPageService.memberDeleteAccount(email);
        return new ResponseTemplate<>(HttpStatus.OK, "회원 탈퇴가 완료되었습니다.", String.format("이메일: %s님 , 회원탈퇴 처리되었습니다.", email));
    }


}
