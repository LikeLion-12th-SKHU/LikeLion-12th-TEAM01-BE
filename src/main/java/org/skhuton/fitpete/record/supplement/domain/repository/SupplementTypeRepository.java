package org.skhuton.fitpete.record.supplement.domain.repository;

import org.skhuton.fitpete.record.supplement.domain.SupplementType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface SupplementTypeRepository extends JpaRepository<SupplementType, Long> {
    List<SupplementType> findBySupplementTypeIdIn(List<Long> ids);
}
