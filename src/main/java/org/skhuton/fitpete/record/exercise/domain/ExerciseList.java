package org.skhuton.fitpete.record.exercise.domain;

import jakarta.persistence.*;
import lombok.*;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.record.calendar.domain.Calendar;
import org.skhuton.fitpete.record.exercise.api.dto.ExerciseListDTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "EXERCISE_LIST")
public class ExerciseList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exercisreId;

    @Column(nullable = false)
    private String exerciseName;

    @Column(nullable = false)
    private int exerciseDuration;  // 운동 시간(분)

    @Column(nullable = false)
    private int exerciseIntensity;  // 운동 강도 (0~100%)

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "CALENDAR_ID")
    private Calendar calendar;

    public ExerciseListDTO toDTO() {
        return ExerciseListDTO.builder()
                .exercisreId(this.exercisreId)
                .exerciseName(this.exerciseName)
                .exerciseDuration(this.exerciseDuration)
                .exerciseIntensity(this.exerciseIntensity)
                .build();
    }
}
