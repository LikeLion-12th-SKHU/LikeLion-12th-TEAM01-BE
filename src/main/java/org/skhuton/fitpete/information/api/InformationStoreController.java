package org.skhuton.fitpete.information.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.skhuton.fitpete.auth.global.template.ResponseTemplate;
import org.skhuton.fitpete.information.api.dto.response.InformationStoreListResponseDto;
import org.skhuton.fitpete.information.application.InformationStoreService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/informations/store")
public class InformationStoreController {

    private final InformationStoreService informationStoreService;

    public InformationStoreController(InformationStoreService informationStoreService) {
        this.informationStoreService = informationStoreService;
    }

    @Operation(summary = "정보글 저장", description = "정보글 저장")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보글 저장 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @PostMapping
    public ResponseTemplate<Void> addInformationStore(@AuthenticationPrincipal String email, @RequestParam Long informationId) {
        informationStoreService.addInformationStore(email, informationId);
        return new ResponseTemplate<>(HttpStatus.OK, "정보글이 성공적으로 저장되었습니다.");
    }

    @Operation(summary = "저장 정보글 조회", description = "저장된 정보글 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "저장한 정보글 조회 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping
    public InformationStoreListResponseDto getStoreInformation(@RequestParam String email) {
        return informationStoreService.getInformationStore(email);
    }

    @Operation(summary = "정보글 저장 취소", description = "저장된 정보글 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보글 저장 취소 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @DeleteMapping
    public ResponseTemplate<Void> cancelInformationStore(@AuthenticationPrincipal String email, @RequestParam Long informationId) {
        informationStoreService.cancelInformationStore(email, informationId);
        return new ResponseTemplate<>(HttpStatus.OK, "정보글 저장이 성공적으로 취소되었습니다.");
    }

}
