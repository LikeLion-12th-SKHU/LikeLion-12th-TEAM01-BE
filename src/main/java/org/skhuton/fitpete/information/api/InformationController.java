package org.skhuton.fitpete.information.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.skhuton.fitpete.auth.global.template.ResponseTemplate;
import org.skhuton.fitpete.auth.global.util.PageableUtil;
import org.skhuton.fitpete.information.application.InformationService;
import org.skhuton.fitpete.information.api.dto.response.InformationInfoResponseDto;
import org.skhuton.fitpete.information.api.dto.response.InformationListResponseDto;
import org.skhuton.fitpete.information.exception.InformationNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<InformationListResponseDto> getAllInformation(
            @Parameter(name = "page", description = "정보글 page", in = ParameterIn.QUERY)
            @RequestParam(defaultValue = "0") int page,
            @Parameter(name = "size", description = "정보글 page size", in = ParameterIn.QUERY)
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = (Pageable) PageableUtil.of(page, size);
        InformationListResponseDto informationList = informationService.getAllInformation(pageable);
        return ResponseEntity.ok(informationList);
    }

    @Operation(summary = "정보 검색(제목)", description = "제목으로 정보 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "제목으로 정보 검색 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/search")
    public ResponseEntity<ResponseTemplate<InformationListResponseDto>> searchInformation(
            @RequestParam String title,
            @Parameter(name = "page", description = "정보글 page", in = ParameterIn.QUERY)
            @RequestParam(defaultValue = "0") int page,
            @Parameter(name = "size", description = "정보글 page size", in = ParameterIn.QUERY)
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageableUtil.of(page, size);
        InformationListResponseDto informationList = informationService.searchInformationByTitle(title, pageable);
        ResponseTemplate<InformationListResponseDto> response = new ResponseTemplate<>(
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
    public ResponseEntity<ResponseTemplate<InformationListResponseDto>> searchInformationByContent(
            @RequestParam("content") String keyword,
            @Parameter(name = "page", description = "정보글 page", in = ParameterIn.QUERY)
            @RequestParam(defaultValue = "0") int page,
            @Parameter(name = "size", description = "정보글 page size", in = ParameterIn.QUERY)
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageableUtil.of(page, size);
        InformationListResponseDto informationList = informationService.searchInformationByContent(keyword, pageable);
        ResponseTemplate<InformationListResponseDto> response = new ResponseTemplate<>(
                HttpStatus.OK,
                "콘텐츠 검색 결과가 성공적으로 조회되었습니다.",
                informationList
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "카테고리별 정보글 검색", description = "카테고리별 정보글 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리별 정보글 검색 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/search/{category}")
    public ResponseTemplate<InformationListResponseDto> categoryByInformationAll(
            @Parameter(name = "category", description = "정보글 카테고리, Category :ALL, DIET, WATER, EXERCISE, SUPPLEMENT, SLEEP, SEX", in = ParameterIn.PATH)
            @PathVariable String category,
            @Parameter(name = "page", description = "정보글 page", in = ParameterIn.QUERY)
            @RequestParam(defaultValue = "0") int page,
            @Parameter(name = "size", description = "정보글 page size", in = ParameterIn.QUERY)
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = (Pageable) PageableUtil.of(page, size);
        InformationListResponseDto information = informationService.categoryByInformationAll(category, pageable);
        return new ResponseTemplate<>(
                HttpStatus.OK,
                "카테고리별 정보글이 성공적으로 조회되었습니다.",
                information
        );
    }

    @Operation(summary = "카테고리 및 제목으로 정보 검색", description = "카테고리 및 제목으로 정보 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 및 제목으로 정보 검색 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/search/category-title")
    public ResponseEntity<ResponseTemplate<InformationListResponseDto>> searchInformationByCategoryAndTitle(
            @Parameter(name = "category", description = "정보글 카테고리, Category :ALL, DIET, WATER, EXERCISE, SUPPLEMENT, SLEEP, SEX", in = ParameterIn.QUERY)
            @RequestParam String category,
            @RequestParam String title,
            @Parameter(name = "page", description = "정보글 page", in = ParameterIn.QUERY)
            @RequestParam(defaultValue = "0") int page,
            @Parameter(name = "size", description = "정보글 page size", in = ParameterIn.QUERY)
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageableUtil.of(page, size);
        InformationListResponseDto informationList = informationService.searchInformationByCategoryAndTitle(category, title, pageable);
        ResponseTemplate<InformationListResponseDto> response = new ResponseTemplate<>(
                HttpStatus.OK,
                "카테고리 및 제목으로 검색한 결과가 성공적으로 조회되었습니다.",
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
    public ResponseEntity<ResponseTemplate<InformationInfoResponseDto>> getInformationById(@PathVariable Long informationId) {
        try {
            InformationInfoResponseDto information = informationService.getInformationById(informationId);
            ResponseTemplate<InformationInfoResponseDto> response = new ResponseTemplate<>(
                    HttpStatus.OK,
                    "상세정보가 성공적으로 조회되었습니다.",
                    information
            );
            return ResponseEntity.ok(response);

        } catch (InformationNotFoundException e) {
            ResponseTemplate<InformationInfoResponseDto> response = new ResponseTemplate<>(
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
