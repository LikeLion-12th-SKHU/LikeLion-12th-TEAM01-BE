package org.skhuton.fitpete.record.calendar.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.record.calendar.api.dto.CalendarDTO;
import org.skhuton.fitpete.record.diet.domain.Diet;
import org.skhuton.fitpete.record.exercise.domain.ExerciseList;
import org.skhuton.fitpete.record.menstrual.domain.Menstrual;
import org.skhuton.fitpete.record.sleep.domain.Sleep;
import org.skhuton.fitpete.record.supplement.domain.Supplement;
import org.skhuton.fitpete.record.water.domain.Water;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CALENDAR_ID")
    @Schema(description = "캘린더 id", example = "1")
    private Long calendarId;

    @Column(name = "DIARY_DATE")
    private String diaryDate;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseList> exerciseLists = new ArrayList<>();

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Water> waterRecords = new ArrayList<>();

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Supplement> supplementRecords = new ArrayList<>();

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diet> dietRecords = new ArrayList<>();

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sleep> sleepRecords = new ArrayList<>();

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menstrual> menstrualRecords = new ArrayList<>();

    public CalendarDTO toDTO() {
        return CalendarDTO.builder()
                .exerciseLists(exerciseLists.stream().map(ExerciseList::toDTO).collect(Collectors.toList()))
                .waterRecords(waterRecords.stream().map(Water::toDTO).collect(Collectors.toList()))
                .supplementRecords(supplementRecords.stream().map(Supplement::toDTO).collect(Collectors.toList()))
                .dietRecords(dietRecords.stream().map(Diet::toDTO).collect(Collectors.toList()))
                .sleepRecords(sleepRecords.stream().map(Sleep::toDTO).collect(Collectors.toList()))
                .menstrualRecords(menstrualRecords.stream().map(Menstrual::toDTO).collect(Collectors.toList()))
                .build();
    }
}
