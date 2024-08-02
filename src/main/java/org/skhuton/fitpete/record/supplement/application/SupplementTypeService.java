package org.skhuton.fitpete.record.supplement.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.supplement.domain.SupplementType;
import org.skhuton.fitpete.record.supplement.domain.SupplementTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SupplementTypeService {
    private final SupplementTypeRepository supplementTypeRepository;

    @Transactional
    public SupplementType addSupplementType(String name) {
        SupplementType supplementType = SupplementType.builder()
                .name(name)
                .build();
        return supplementTypeRepository.save(supplementType);
    }
}
