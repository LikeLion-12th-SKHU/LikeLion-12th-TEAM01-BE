package org.skhuton.fitpete.community.board.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.skhuton.fitpete.auth.global.template.ResponseTemplate;
import org.skhuton.fitpete.auth.global.util.PageableUtil;
import org.skhuton.fitpete.community.board.api.dto.request.BoardSaveRequestDTO;
import org.skhuton.fitpete.community.board.api.dto.request.BoardUpdateRequestDTO;
import org.skhuton.fitpete.community.board.api.dto.response.BoardInfoResponseDTO;
import org.skhuton.fitpete.community.board.api.dto.response.BoardListResponseDTO;
import org.skhuton.fitpete.community.board.application.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @Operation(summary = "커뮤니티 글 등록", description = "커뮤니티 글 등록, Category : ALL, DIET, WATER, EXERCISE, SUPPLEMENT, SLEEP, SEX")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "커뮤니티 글 등록 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PostMapping("/write")
    public ResponseTemplate<String> boardSave(@AuthenticationPrincipal String email,
                                              @Valid @RequestBody BoardSaveRequestDTO boardSaveRequestDTO,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return new ResponseTemplate<>(HttpStatus.BAD_REQUEST, "유효성 검사 오류", errorMessage);
        }

        Long boardId = boardService.boardSave(email, boardSaveRequestDTO);
        return new ResponseTemplate<>(HttpStatus.CREATED, "커뮤니티 글 등록", String.format("%d번 커뮤니티 글 등록 !", boardId));
    }

    @Operation(summary = "카테고리별 커뮤니티 글 검색", description = "카테고리별 커뮤니티 글 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "커뮤니티 글 검색 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @GetMapping("/{category}")
    public ResponseTemplate<BoardListResponseDTO> categoryByBoardAll(
            @AuthenticationPrincipal String email,
            @Parameter(name = "category", description = "커뮤니티 글 카테고리, Category :ALL, DIET, WATER, EXERCISE, SUPPLEMENT, SLEEP, SEX", in = ParameterIn.PATH)
            @PathVariable String category,
            @Parameter(name = "page", description = "커뮤니티 page", in = ParameterIn.QUERY)
            @RequestParam(defaultValue = "0") int page,
            @Parameter(name = "size", description = "커뮤니티 page size", in = ParameterIn.QUERY)
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = (Pageable) PageableUtil.of(page, size);
        BoardListResponseDTO board = boardService.categoryByBoardAll(email, category, pageable);
        return new ResponseTemplate<>(HttpStatus.OK, "카테고리별 커뮤니티 글 조회", board);
    }

    @Operation(summary = "커뮤니티 글 상세 조회", description = "커뮤니티 글 상세 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "커뮤니티 글 상세 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @GetMapping("/detail/{boardId}")
    public ResponseTemplate<BoardInfoResponseDTO> boardDetail(@AuthenticationPrincipal String email,
                                                              @PathVariable(name = "boardId") Long boardId) {
        return new ResponseTemplate<>(HttpStatus.OK, "커뮤니티 글 상세 조회", boardService.boardDetail(email, boardId));
    }

    @Operation(summary = "커뮤니티 글 삭제", description = "커뮤니티 글 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "커뮤니티 글 삭제 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @DeleteMapping("/{boardId}")
    public ResponseTemplate<Void> boardDelete(@AuthenticationPrincipal String email,
                                              @PathVariable(name = "boardId") Long boardId) {
        boardService.boardDelete(email, boardId);
        return new ResponseTemplate<>(HttpStatus.OK, "커뮤니티 글 삭제");
    }

    @Operation(summary = "커뮤니티 글 수정", description = "커뮤니티 글 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "커뮤니티 글 수정 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PutMapping("/{boardId}")
    public ResponseTemplate<BoardInfoResponseDTO> boardUpdate(@AuthenticationPrincipal String email,
                                                              @PathVariable(name = "boardId") Long boardId,
                                                              @RequestBody BoardUpdateRequestDTO boardUpdateRequestDTO) {
        return new ResponseTemplate<>(HttpStatus.OK, "커뮤니티 글 수정", boardService.boardUpdate(email, boardId, boardUpdateRequestDTO));
    }

    @Operation(summary = "커뮤니티 글 신고", description = "커뮤니티 글 신고")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "커뮤니티 글 신고 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PostMapping("/report")
    public ResponseTemplate<String> boardReport(@AuthenticationPrincipal String email,
                                                @RequestParam(name = "boardId") Long boardId) {
        boardService.boardReport(email, boardId);
        return new ResponseTemplate<>(HttpStatus.OK, "커뮤니티 글 신고", String.format("%d번 커뮤니티 글 신고", boardId));
    }
}
