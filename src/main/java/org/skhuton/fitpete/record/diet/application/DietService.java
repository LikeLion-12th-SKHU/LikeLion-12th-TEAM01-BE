package org.skhuton.fitpete.record.diet.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.diet.api.dto.DietDTO;
import org.skhuton.fitpete.record.diet.domain.Diet;
import org.skhuton.fitpete.record.diet.domain.repository.DietRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DietService {
    private final DietRepository dietRepository;

    @Transactional
    public void saveDiet(DietDTO dietDTO) {
        Diet diet = Diet.builder()
                .foodDescription(dietDTO.foodDescription())
                .build();
        dietRepository.save(diet);
    }
}
