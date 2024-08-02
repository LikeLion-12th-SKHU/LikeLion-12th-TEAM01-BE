package org.skhuton.fitpete.record.water.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.water.api.dto.WaterDTO;
import org.skhuton.fitpete.record.water.domain.Water;
import org.skhuton.fitpete.record.water.domain.repository.WaterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WaterService {
    private final WaterRepository waterRepository;

    @Transactional
    public void saveWater(WaterDTO waterDTO) {
        Water water = Water.builder()
                .waterIntake(waterDTO.waterIntake())
                .build();
        waterRepository.save(water);
    }
}
