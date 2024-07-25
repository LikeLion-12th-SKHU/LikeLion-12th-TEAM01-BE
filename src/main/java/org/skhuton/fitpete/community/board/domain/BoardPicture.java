package org.skhuton.fitpete.community.board.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_PICTURE_ID")
    @Schema(description = "게시글의 사진 id", example = "1")
    private Long boardPictureId;

    @Schema(description = "게시글 이미지 URL", example = "https://...")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @Builder
    private BoardPicture(String imageUrl, Board board) {
        this.imageUrl = imageUrl;
        this.board = board;
    }

}

