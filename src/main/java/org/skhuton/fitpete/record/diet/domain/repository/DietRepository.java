package org.skhuton.fitpete.record.diet.domain.repository;

import org.skhuton.fitpete.record.diet.domain.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietRepository extends JpaRepository<Diet, Long> {
    List<Diet> findByMember_MemberId(Long memberId);
}
