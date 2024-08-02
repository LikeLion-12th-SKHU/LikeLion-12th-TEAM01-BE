package org.skhuton.fitpete.record.supplement.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.supplement.domain.SupplementCalendar;
import org.skhuton.fitpete.record.supplement.domain.SupplementType;
import org.skhuton.fitpete.record.calendar.domain.Calendar;
import org.skhuton.fitpete.record.calendar.domain.repository.CalendarRepository;
import org.skhuton.fitpete.record.supplement.domain.SupplementCalendarRepository;
import org.skhuton.fitpete.record.supplement.domain.SupplementTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SupplementService {
    private final SupplementCalendarRepository supplementCalendarRepository;
    private final SupplementTypeRepository supplementTypeRepository;
    private final CalendarRepository calendarRepository;

    @Transactional
    public void saveSupplement(Long calendarId, Long supplementTypeId, Boolean tookSupplements) {
        Calendar calendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new RuntimeException("Calendar not found"));
        SupplementType supplementType = supplementTypeRepository.findById(supplementTypeId)
                .orElseThrow(() -> new RuntimeException("SupplementType not found"));

        SupplementCalendar supplementCalendar = SupplementCalendar.builder()
                .calendar(calendar)
                .supplementType(supplementType)
                .tookSupplements(tookSupplements)
                .build();

        supplementCalendarRepository.save(supplementCalendar);
    }
}
