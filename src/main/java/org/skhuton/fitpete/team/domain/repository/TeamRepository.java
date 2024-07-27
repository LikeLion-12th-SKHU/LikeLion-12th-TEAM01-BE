package org.skhuton.fitpete.team.domain.repository;

import org.skhuton.fitpete.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    boolean existsByTeamName(String name);
}
