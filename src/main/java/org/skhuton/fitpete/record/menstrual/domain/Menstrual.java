package org.skhuton.fitpete.record.menstrual.domain;

import jakarta.persistence.*;
import lombok.*;
import org.skhuton.fitpete.record.calendar.domain.Calendar;
import org.skhuton.fitpete.record.menstrual.api.dto.MenstrualDTO;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menstrual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENSTRUAL_ID")
    private Long menstrualId;

    @ManyToOne
    @JoinColumn(name = "CALENDAR_ID")
    private Calendar calendar;

    private Boolean menstrualCycle;
    private LocalDate menstrualStartDate;
    private LocalDate menstrualEndDate;

    public MenstrualDTO toDTO() {
        return MenstrualDTO.builder()
                .menstrualCycle(menstrualCycle)
                .menstrualStartDate(menstrualStartDate)
                .menstrualEndDate(menstrualEndDate)
                .build();
    }
}