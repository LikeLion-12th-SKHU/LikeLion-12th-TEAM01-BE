package org.skhuton.fitpete.information.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.skhuton.fitpete.auth.global.template.ResponseTemplate;
import org.skhuton.fitpete.information.application.InformationService;
import org.skhuton.fitpete.information.domain.Information;
import org.skhuton.fitpete.information.exception.InformationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/informations")
public class InformationController {

    private final InformationService informationService;
    public InformationController(InformationService informationService) { this.informationService = informationService; }

    @Operation(summary = "정보 목록 조회", description = "정보 전체 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보 목록 조회 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping
    public ResponseEntity<List<Information>> getAllInformation() {
        List<Information> informationList = informationService.getAllInformation();
        return ResponseEntity.ok(informationList);
    }

    @Operation(summary = "정보 검색(제목)", description = "제목으로 정보 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "제목으로 정보 검색 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/search")
    public ResponseEntity<ResponseTemplate<List<Information>>> searchInformation(@RequestParam String title) {
        List<Information> informationList = informationService.searchInformationByTitle(title);
        ResponseTemplate<List<Information>> response = new ResponseTemplate<>(
                HttpStatus.OK,
                "검색 결과가 성공적으로 조회되었습니다.",
                informationList
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "정보 검색(내용)", description = "내용으로 정보 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "내용으로 정보 검색 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/search/content")
    public ResponseEntity<ResponseTemplate<List<Information>>> searchInformationByContent(@RequestParam("content") String keyword) {
        List<Information> informationList = informationService.searchInformationByContent(keyword);
        ResponseTemplate<List<Information>> response = new ResponseTemplate<>(
                HttpStatus.OK,
                "콘텐츠 검색 결과가 성공적으로 조회되었습니다.",
                informationList
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "상세 정보", description = "상세 정보 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상세 정보 조회 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/{informationId}")
    public ResponseEntity<ResponseTemplate<Information>> getInformationById(@PathVariable Long informationId) {
        try {
            Information information = informationService.getInformationById(informationId);
            ResponseTemplate<Information> response = new ResponseTemplate<>(
                    HttpStatus.OK,
                    "상세정보가 성공적으로 조회되었습니다.",
                    information
            );
            return ResponseEntity.ok(response);

        } catch (InformationNotFoundException e) {
            ResponseTemplate<Information> response = new ResponseTemplate<>(
                    HttpStatus.NOT_FOUND,
                    "정보를 찾을 수 없습니다."
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @Operation(summary = "정보 삭제", description = "정보 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보 삭제 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @DeleteMapping("/{informationId}")
    public ResponseEntity<ResponseTemplate<Void>> deleteInformationById(@PathVariable Long informationId) {
        informationService.removeInformation(informationId);
        ResponseTemplate<Void> response = new ResponseTemplate<>(
                HttpStatus.OK,
                "정보가 성공적으로 삭제되었습니다."
        );
        return ResponseEntity.ok(response);
    }

}
