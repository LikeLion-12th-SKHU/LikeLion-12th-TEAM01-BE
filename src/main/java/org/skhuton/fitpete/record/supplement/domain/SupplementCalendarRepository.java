package org.skhuton.fitpete.record.supplement.domain;

import org.skhuton.fitpete.record.supplement.domain.SupplementCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplementCalendarRepository extends JpaRepository<SupplementCalendar, Long> {
}
