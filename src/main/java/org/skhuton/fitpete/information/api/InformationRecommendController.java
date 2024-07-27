package org.skhuton.fitpete.information.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.skhuton.fitpete.auth.global.template.ResponseTemplate;
import org.skhuton.fitpete.information.application.InformationRecommendService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/informations/recommend")
public class InformationRecommendController {

    private final InformationRecommendService informationRecommendService;

    public InformationRecommendController(InformationRecommendService informationRecommendService) {
        this.informationRecommendService = informationRecommendService;
    }

    @Operation(summary = "정보글 추천", description = "정보글 추천")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보글 추천 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PostMapping("/recommend")
    public ResponseTemplate<Void> addInformationRecommend(@AuthenticationPrincipal String email, @RequestParam Long informationId) {
        informationRecommendService.addInformationRecommend(email, informationId);
        return new ResponseTemplate<>(HttpStatus.OK, "정보글이 성공적으로 추천되었습니다.");
    }

    @Operation(summary = "정보글 추천 취소", description = "정보글 추천 취소")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보글 추천 취소 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PostMapping("/cancel")
    public ResponseTemplate<Void> cancelInformationRecommend(@AuthenticationPrincipal String email, @RequestParam Long informationId) {
        informationRecommendService.cancelInformationRecommend(email, informationId);
        return new ResponseTemplate<>(HttpStatus.OK, "정보글 추천이 성공적으로 취소되었습니다.");
    }

}
