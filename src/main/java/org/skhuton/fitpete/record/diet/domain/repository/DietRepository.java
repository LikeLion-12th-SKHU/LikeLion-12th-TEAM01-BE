package org.skhuton.fitpete.record.diet.domain.repository;

import org.skhuton.fitpete.record.diet.domain.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepository extends JpaRepository<Diet, Long> {
}
