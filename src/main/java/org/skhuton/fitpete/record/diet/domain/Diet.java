package org.skhuton.fitpete.record.diet.domain;

import jakarta.persistence.*;
import lombok.*;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.record.calendar.domain.Calendar;
import org.skhuton.fitpete.record.diet.api.dto.DietDTO;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIET_ID")
    private Long dietId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "CALENDAR_ID")
    private Calendar calendar;

    private String foodDescription;

    private String photoUrl;

    public DietDTO toDTO() {
        return DietDTO.builder()
                .foodDescription(foodDescription)
                .photoUrl(photoUrl)
                .build();
    }
}
