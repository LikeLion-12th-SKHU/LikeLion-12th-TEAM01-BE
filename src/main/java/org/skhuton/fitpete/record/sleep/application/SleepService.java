package org.skhuton.fitpete.record.sleep.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.sleep.api.dto.SleepDTO;
import org.skhuton.fitpete.record.sleep.domain.Sleep;
import org.skhuton.fitpete.record.sleep.domain.repository.SleepRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SleepService {
    private final SleepRepository sleepRepository;

    @Transactional
    public void saveSleep(SleepDTO sleepDTO) {
        Sleep sleep = Sleep.builder()
                .sleepHours(sleepDTO.sleepHours())
                .build();
        sleepRepository.save(sleep);
    }
}
