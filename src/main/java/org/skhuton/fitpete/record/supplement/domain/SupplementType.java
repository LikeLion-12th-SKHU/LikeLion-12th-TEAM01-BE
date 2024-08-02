package org.skhuton.fitpete.record.supplement.domain;

import jakarta.persistence.*;
import lombok.*;
import org.skhuton.fitpete.record.supplement.api.dto.SupplementTypeDTO;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SupplementType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLEMENT_TYPE_ID")
    private Long supplementTypeId;

    @Column(name = "NAME")
    private String name;

    public SupplementTypeDTO toDTO() {
        return SupplementTypeDTO.builder()
                .supplementTypeId(supplementTypeId)
                .name(name)
                .build();
    }
}
