package org.skhuton.fitpete.member.domain.repository;

import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String memberEmail);
    Boolean existsByNickname(String nickname);
    List<Team> findTeamsByMemberId(Long memberId);
}
