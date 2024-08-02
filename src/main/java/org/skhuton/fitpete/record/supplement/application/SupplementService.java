package org.skhuton.fitpete.record.supplement.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.supplement.api.dto.SupplementDTO;
import org.skhuton.fitpete.record.supplement.domain.Supplement;
import org.skhuton.fitpete.record.supplement.domain.repository.SupplementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SupplementService {
    private final SupplementRepository supplementRepository;

    @Transactional
    public void saveSupplement(SupplementDTO supplementDTO) {
        Supplement supplement = Supplement.builder()
                .tookSupplements(supplementDTO.tookSupplements())
                .build();
        supplementRepository.save(supplement);
    }
}
