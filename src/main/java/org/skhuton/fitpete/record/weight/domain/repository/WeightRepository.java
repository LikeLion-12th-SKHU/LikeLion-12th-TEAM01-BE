package org.skhuton.fitpete.record.weight.domain.repository;

import org.skhuton.fitpete.record.weight.domain.Weight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeightRepository extends JpaRepository<Weight, Long> {
    List<Weight> findByMember_MemberId(Long memberId);
}
