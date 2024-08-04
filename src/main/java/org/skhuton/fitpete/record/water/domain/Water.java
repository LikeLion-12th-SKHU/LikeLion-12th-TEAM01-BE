package org.skhuton.fitpete.record.water.domain;

import jakarta.persistence.*;
import lombok.*;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.record.calendar.domain.Calendar;
import org.skhuton.fitpete.record.water.api.dto.WaterDTO;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "WATER")
public class Water {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WATER_ID")
    private Long waterId;

    @ManyToOne
    @JoinColumn(name = "CALENDAR_ID")
    private Calendar calendar;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private Long waterIntake;

    public WaterDTO toDTO() {
        return WaterDTO.builder()
                .waterId(this.waterId)
                .waterIntake(this.waterIntake)
                .build();
    }
}
