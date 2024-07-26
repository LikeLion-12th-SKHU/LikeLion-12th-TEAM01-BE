package org.skhuton.fitpete.community.board.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.skhuton.fitpete.auth.global.template.ResponseTemplate;
import org.skhuton.fitpete.community.board.application.BoardRecommendService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board/recommend")
public class BoardRecommendController {
    private final BoardRecommendService boardRecommendService;

    public BoardRecommendController(BoardRecommendService boardRecommendService) {
        this.boardRecommendService = boardRecommendService;
    }

    @Operation(summary = "커뮤니티 글 추천", description = "커뮤니티 글 추천")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "커뮤니티 글 추천 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PostMapping("/recommend")
    public ResponseTemplate<Void> addBoardLike(@AuthenticationPrincipal String email, @RequestParam Long boardId) {
        boardRecommendService.addBoardRecommend(email, boardId);
        return new ResponseTemplate<>(HttpStatus.OK, "커뮤니티 글 추천");
    }

    @Operation(summary = "커뮤니티 글 추천 취소", description = "커뮤니티 글 추천 취소")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 좋아요 취소 성공 !"),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PostMapping("/cancel")
    public ResponseTemplate<Void> cancelBoardLike(@AuthenticationPrincipal String email, @RequestParam Long boardId) {
        boardRecommendService.cancelBoardRecommend(email, boardId);
        return new ResponseTemplate<>(HttpStatus.OK, "커뮤니티 글 추천 취소");
    }

}
