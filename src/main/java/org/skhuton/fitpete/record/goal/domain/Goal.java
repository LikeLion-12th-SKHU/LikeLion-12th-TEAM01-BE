package org.skhuton.fitpete.record.goal.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GOAL_ID")
    private Long goalId;

    @Schema(name = "GOAL_WEIGHT", example = "1")
    private int goalWeigh;

    @Schema(name = "GOAL_WATER", example = "1")
    private int goalWater;

    @Schema(name = "GOAL_EXERCISE_DURATION", example = "1")
    private int goalExerciseDuration;

    @Schema(name = "GOAL_EXERCISE_ROBBERY", example = "1")
    private int goalExerciseRobbery;

    @Schema(name = "GOAL_SLEEP", example = "1")
    private int goalSleep;

    @Schema(name = "GOAL_SUPPLEMENT_LIST", example = "비타민c")
    private String supplementList;

}
