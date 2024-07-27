package org.skhuton.fitpete.information.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INFO_ID")
    @Schema(description = "", example = "")
    private Long informationId;

    @Schema(description = "정보 제목", example = "비타민C")
    private String title;

    @Schema(description = "정보 내용", example = "효능, 주의할 점 등")
    private String content;

    @Schema(description = "작성일", example = "2024.07.25")
    private String createdAt;

    @Schema(description = "작성자", example = "홍길동")
    private String createdBy;

    @Schema(description = "수정일", example = "2024.07.06")
    private String modifiedAt;

    @Schema(description = "추천 수", example = "1")
    private int recommendCount;

    @Builder
    public Information(String title, String content, String createdAt, String createdBy, String modifiedAt, int recommendCount) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.recommendCount = recommendCount;
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

}
