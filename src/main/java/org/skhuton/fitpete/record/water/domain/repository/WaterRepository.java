package org.skhuton.fitpete.record.water.domain.repository;

import org.skhuton.fitpete.record.water.domain.Water;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaterRepository extends JpaRepository<Water, Long> {
    List<Water> findByMember_MemberId(Long memberId);
}
