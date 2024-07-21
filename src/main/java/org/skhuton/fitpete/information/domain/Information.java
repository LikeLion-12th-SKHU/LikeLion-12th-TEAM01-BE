package org.skhuton.fitpete.information.domain;

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
    @Column(name = "INFO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "INFO_TITLE")
    private String title;

    @Column(name = "INFO_CONTENT")
    private String content;

    @Column(name = "INFO_CREATEDAT")
    private String createdAt;

    @Column(name = "INFO_CREATEDBY")
    private String createdBy;

    @Column(name = "INFO_MODIFIEDAT")
    private String modifiedAt;


}
