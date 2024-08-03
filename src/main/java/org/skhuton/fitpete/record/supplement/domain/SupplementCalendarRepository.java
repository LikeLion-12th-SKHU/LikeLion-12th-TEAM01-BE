package org.skhuton.fitpete.record.supplement.domain;

import org.skhuton.fitpete.record.supplement.domain.SupplementCalendar;
import org.skhuton.fitpete.record.water.domain.Water;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplementCalendarRepository extends JpaRepository<SupplementCalendar, Long> {
    List<SupplementCalendar> findByMember_MemberId(Long memberId);

}
