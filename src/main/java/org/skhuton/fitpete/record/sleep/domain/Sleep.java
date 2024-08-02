package org.skhuton.fitpete.record.sleep.domain;

import jakarta.persistence.*;
import lombok.*;
import org.skhuton.fitpete.record.calendar.domain.Calendar;
import org.skhuton.fitpete.record.sleep.api.dto.SleepDTO;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sleep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SLEEP_ID")
    private Long sleepId;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    private Double sleepHours;

    public SleepDTO toDTO() {
        return SleepDTO.builder()
                .sleepHours(sleepHours)
                .build();
    }
}
