package org.skhuton.fitpete.community.board.domain;

import lombok.Getter;

@Getter
public enum Category {
    DIET("다이어트"),
    WATER("물"),
    EXERCISE("운동"),
    SUPPLEMENT("영양제"),
    SLEEP("잠"),
    SEX("성건강"),
    SUGGESTION("건의사항");

    private final String name;

    Category(String name) {
        this.name = name;
    }

}
