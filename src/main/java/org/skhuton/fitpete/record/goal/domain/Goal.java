package org.skhuton.fitpete.record.goal.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.skhuton.fitpete.member.domain.Member;

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

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public Goal(int goalWeigh, int goalWater, int goalExerciseDuration, int goalExerciseRobbery, int goalSleep, Member member) {
        this.goalWeigh = goalWeigh;
        this.goalWater = goalWater;
        this.goalExerciseDuration = goalExerciseDuration;
        this.goalExerciseRobbery = goalExerciseRobbery;
        this.goalSleep = goalSleep;
        this.member = member;
    }
}
