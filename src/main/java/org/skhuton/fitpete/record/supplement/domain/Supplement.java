package org.skhuton.fitpete.record.supplement.domain;

import jakarta.persistence.*;
import lombok.*;
import org.skhuton.fitpete.record.calendar.domain.Calendar;
import org.skhuton.fitpete.record.supplement.api.dto.SupplementDTO;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Supplement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLEMENT_ID")
    private Long supplementId;

    @ManyToOne
    @JoinColumn(name = "CALENDAR_ID")
    private Calendar calendar;

    private Boolean tookSupplements;

    public SupplementDTO toDTO() {
        return SupplementDTO.builder()
                .tookSupplements(tookSupplements)
                .build();
    }
}
