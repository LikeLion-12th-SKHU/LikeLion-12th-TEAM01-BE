package org.skhuton.fitpete.record.menstrual.domain.repository;

import org.skhuton.fitpete.record.menstrual.domain.Menstrual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenstrualRepository extends JpaRepository<Menstrual, Long> {
}
