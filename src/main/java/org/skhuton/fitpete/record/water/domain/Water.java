package org.skhuton.fitpete.record.water.domain;

import jakarta.persistence.*;
import lombok.*;
import org.skhuton.fitpete.record.calendar.domain.Calendar;
import org.skhuton.fitpete.record.water.api.dto.WaterDTO;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Water {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WATER_ID")
    private Long waterId;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    private Long waterIntake;

    public WaterDTO toDTO() {
        return WaterDTO.builder()
                .waterIntake(waterIntake)
                .build();
    }
}
