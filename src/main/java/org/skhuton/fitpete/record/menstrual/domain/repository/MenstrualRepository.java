package org.skhuton.fitpete.record.menstrual.domain.repository;

import org.skhuton.fitpete.record.menstrual.domain.Menstrual;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenstrualRepository extends JpaRepository<Menstrual, Long> {
    List<Menstrual> findByMember_MemberId(Long memberId);
}
