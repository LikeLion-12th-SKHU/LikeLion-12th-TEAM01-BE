package org.skhuton.fitpete.community.board.api.dto.response;

import lombok.Builder;
import org.skhuton.fitpete.community.board.domain.Board;
import org.skhuton.fitpete.community.board.domain.BoardPicture;
import org.skhuton.fitpete.community.board.domain.Category;
import org.skhuton.fitpete.community.comment.api.dto.response.CommentInfoResponseDTO;
import org.skhuton.fitpete.community.comment.domain.Comment;
import org.skhuton.fitpete.member.domain.Member;


import java.util.List;

@Builder
public record BoardInfoResponseDTO(
        Long myMemberId,
        Long writerMemberId,
        Long boardId,
        Category category,
        String title,
        String content,
        List<String> imageUrl,
        int likeCount,
        boolean isLike,
        int commentCount,
        String nickname,
        String date,
        List<CommentInfoResponseDTO> comments
) {
    public static BoardInfoResponseDTO of (Member member, Board board) {
        List<String> imageUrl = board.getPictures().stream()
                .map(BoardPicture::getImageUrl)
                .toList();

        return BoardInfoResponseDTO.builder()
                .myMemberId(member.getMemberId())
                .writerMemberId(board.getWriter().getMemberId())
                .boardId(board.getBoardId())
                .category(board.getCategory())
                .title(board.getTitle())
                .content(board.getContent())
                .imageUrl(imageUrl)
                .likeCount(board.getRecommendCount())
                .commentCount(board.getComments().size())
                .nickname(board.getWriter().getNickname())
                .date(board.getBoardDate())
                .build();


    }




    public static BoardInfoResponseDTO detailOf(Member member, Board board, boolean isLike) {
        List<String> imageUrl = board.getPictures().stream()
                .map(BoardPicture::getImageUrl)
                .toList();

        List<CommentInfoResponseDTO> commentInfoResponseDTO = board.getComments().stream()
                .map(CommentInfoResponseDTO::of)
                .toList();

        return BoardInfoResponseDTO.builder()
                .myMemberId(member.getMemberId())
                .writerMemberId(board.getWriter().getMemberId())
                .boardId(board.getBoardId())
                .category(board.getCategory())
                .title(board.getTitle())
                .content(board.getContent())
                .imageUrl(imageUrl)
                .likeCount(board.getRecommendCount())
                .isLike(isLike)
                .commentCount(commentInfoResponseDTO.size())
                .nickname(board.getWriter().getNickname())
                .date(board.getBoardDate())
                .comments(commentInfoResponseDTO)
                .build();
    }
}
