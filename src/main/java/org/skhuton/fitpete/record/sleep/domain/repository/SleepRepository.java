package org.skhuton.fitpete.record.sleep.domain.repository;

import org.skhuton.fitpete.record.sleep.domain.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SleepRepository extends JpaRepository<Sleep, Long> {
}
