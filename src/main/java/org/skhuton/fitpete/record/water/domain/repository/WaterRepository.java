package org.skhuton.fitpete.record.water.domain.repository;

import org.skhuton.fitpete.record.water.domain.Water;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterRepository extends JpaRepository<Water, Long> {
}
