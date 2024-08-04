package org.skhuton.fitpete.record.goal.domain.repository;

import org.skhuton.fitpete.record.goal.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}
