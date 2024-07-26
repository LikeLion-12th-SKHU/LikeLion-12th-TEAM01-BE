package org.skhuton.fitpete.community.comment.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.auth.global.template.ResponseTemplate;
import org.skhuton.fitpete.community.comment.api.dto.request.CommentSaveRequestDTO;
import org.skhuton.fitpete.community.comment.api.dto.request.CommentUpdateRequestDTO;
import org.skhuton.fitpete.community.comment.api.dto.response.CommentInfoResponseDTO;
import org.skhuton.fitpete.community.comment.application.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community/comment")
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "댓글 등록", description = "댓글을 등록 합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 성공"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PostMapping("/")
    public ResponseTemplate<String> commentSave(@AuthenticationPrincipal String email,
                                                @RequestBody CommentSaveRequestDTO commentSaveRequestDTO) {
        commentService.commentSave(email, commentSaveRequestDTO);
        return new ResponseTemplate<>(HttpStatus.CREATED, "댓글 등록",
                String.format("%d 게시글의 댓글입니다.", commentSaveRequestDTO.boardId()));
    }

    @Operation(summary = "댓글 수정", description = "댓글을 수정 합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 수정"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @PutMapping("/{commentId}")
    public ResponseTemplate<CommentInfoResponseDTO> commentUpdate(@AuthenticationPrincipal String email,
                                                             @PathVariable("commentId") Long commentId,
                                                             @RequestBody CommentUpdateRequestDTO commentUpdateRequestDTO) {
        return new ResponseTemplate<>(HttpStatus.OK, "댓글 수정",
                commentService.commentUpdate(email, commentId, commentUpdateRequestDTO));
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제 합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 삭제"),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    @DeleteMapping("/{commentId}")
    public ResponseTemplate<String> commentDelete(@AuthenticationPrincipal String email,
                                             @PathVariable("commentId") Long commentId) {
        commentService.commentDelete(email, commentId);
        return new ResponseTemplate<>(HttpStatus.OK, "댓글 삭제", "댓글이 삭제되었습니다.");
    }

}