package org.skhuton.fitpete.record.supplement.domain.repository;

import org.skhuton.fitpete.record.supplement.domain.Supplement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplementRepository extends JpaRepository<Supplement, Long> {
}
