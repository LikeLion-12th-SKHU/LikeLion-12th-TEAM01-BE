package org.skhuton.fitpete.record.sleep.domain.repository;

import org.skhuton.fitpete.record.sleep.domain.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SleepRepository extends JpaRepository<Sleep, Long> {
    List<Sleep> findByMember_MemberId(Long memberId);
}
