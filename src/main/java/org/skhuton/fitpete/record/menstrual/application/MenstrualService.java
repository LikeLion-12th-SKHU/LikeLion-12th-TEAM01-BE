package org.skhuton.fitpete.record.menstrual.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.menstrual.api.dto.MenstrualDTO;
import org.skhuton.fitpete.record.menstrual.domain.Menstrual;
import org.skhuton.fitpete.record.menstrual.domain.repository.MenstrualRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenstrualService {
    private final MenstrualRepository menstrualRepository;

    @Transactional
    public void saveMenstrual(MenstrualDTO menstrualDTO) {
        Menstrual menstrual = Menstrual.builder()
                .menstrualCycle(menstrualDTO.menstrualCycle())
                .menstrualStartDate(menstrualDTO.menstrualStartDate())
                .menstrualEndDate(menstrualDTO.menstrualEndDate())
                .build();
        menstrualRepository.save(menstrual);
    }
}
