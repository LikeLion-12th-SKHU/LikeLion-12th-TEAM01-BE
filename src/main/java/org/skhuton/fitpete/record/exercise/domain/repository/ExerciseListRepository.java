package org.skhuton.fitpete.record.exercise.domain.repository;

import org.skhuton.fitpete.record.exercise.domain.ExerciseList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseListRepository extends JpaRepository<ExerciseList, Long> {
    List<ExerciseList> findByMember_MemberId(Long memberId);
}
