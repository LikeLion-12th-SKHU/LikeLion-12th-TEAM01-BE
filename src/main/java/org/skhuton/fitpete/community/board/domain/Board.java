package org.skhuton.fitpete.community.board.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.skhuton.fitpete.community.board.api.dto.request.BoardUpdateRequestDTO;
import org.skhuton.fitpete.community.comment.domain.Comment;
import org.skhuton.fitpete.member.domain.Member;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    @Schema(description = "게시글 id", example = "1")
    private Long boardId;

    @Enumerated(value = EnumType.STRING)
    @Schema(description = "카테고리", example = "다이어트, 운동, 자유")
    private Category category;

    @Schema(description = "게시글 제목", example = "제목")
    private String title;

    @Schema(description = "게시글 내용", example = "내용")
    @Column(columnDefinition = "TEXT")
    private String content;

    @Schema(description = "게시글 날짜", example = "2024.06.21")
    private String boardDate;

    @Schema(description = "신고 횟수", example = "1")
    private int reportCount;

    @Schema(description = "좋아요 개수", example = "1")
    private int recommendCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @Schema(description = "작성자", example = "nickname")
    private Member writer;

    @OneToMany(mappedBy = "board", orphanRemoval = true, cascade = CascadeType.ALL)
    @Schema(description = "이미지")
    private List<BoardPicture> pictures = new ArrayList<>();

    @OneToMany(mappedBy = "board", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    private Board(Category category, String title, String content, Member writer) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.boardDate = LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.reportCount = 0;
        this.recommendCount = 0;
        this.writer = writer;
    }

    public void boardUpdate(BoardUpdateRequestDTO boardUpdateRequestDTO) {
        this.title = boardUpdateRequestDTO.title();
        this.category = boardUpdateRequestDTO.category();
        this.content = boardUpdateRequestDTO.content();
    }

    public void updateRecommendCount() {
        this.recommendCount++;
    }

    public void cancelRecommendCount() {
        if (this.recommendCount <= 0) {
            this.recommendCount = 0;
        } else {
            this.recommendCount--;
        }
    }

    public void updateBoardReportCount() {
        this.reportCount++;
    }

}