package org.skhuton.fitpete.record.exercise.domain.repository;

import org.skhuton.fitpete.record.exercise.domain.ExerciseList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseListRepository extends JpaRepository<ExerciseList, Long> {
}
