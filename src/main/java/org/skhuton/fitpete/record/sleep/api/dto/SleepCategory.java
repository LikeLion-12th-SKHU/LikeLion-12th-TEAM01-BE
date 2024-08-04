package org.skhuton.fitpete.record.sleep.api.dto;

public enum SleepCategory {
    LESS_THAN_SIX_HOURS("6시간 이하"),
    SEVEN_TO_EIGHT_HOURS("7~8시간"),
    MORE_THAN_NINE_HOURS("9시간 이상");

    private final String description;

    SleepCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
