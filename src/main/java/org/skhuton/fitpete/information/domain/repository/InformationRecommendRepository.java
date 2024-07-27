package org.skhuton.fitpete.information.domain.repository;

import org.skhuton.fitpete.information.domain.Information;
import org.skhuton.fitpete.information.domain.InformationRecommend;
import org.skhuton.fitpete.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InformationRecommendRepository extends JpaRepository<InformationRecommend, Long> {
    boolean existsByInformationAndMember(Information information, Member member);

    Optional<InformationRecommend> findByInformationAndMember(Information information, Member member);
}
