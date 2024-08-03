package org.skhuton.fitpete.record.supplement.domain;

import jakarta.persistence.*;
import lombok.*;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.record.calendar.domain.Calendar;
import org.skhuton.fitpete.record.supplement.api.dto.SupplementDTO;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SupplementCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLEMENT_CALENDAR_ID")
    private Long supplementCalendarId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "SUPPLEMENT_TYPE_ID")
    private SupplementType supplementType;

    @ManyToOne
    @JoinColumn(name = "CALENDAR_ID")
    private Calendar calendar;

    private Boolean tookSupplements;

    public SupplementDTO toDTO() {
        return SupplementDTO.builder()
                .supplementType(supplementType.toDTO())
                .tookSupplements(tookSupplements)
                .build();
    }
}
