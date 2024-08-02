package org.skhuton.fitpete.information.domain.repository;

import org.skhuton.fitpete.information.domain.Information;
import org.skhuton.fitpete.information.domain.InformationStore;
import org.skhuton.fitpete.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface InformationStoreRepository extends JpaRepository<InformationStore, Long> {

    boolean existsByInformationAndMember(Information information, Member member);
    Optional<InformationStore> findByInformationAndMember(Information information, Member member);
    List<InformationStore> findInformationStoreByMember(@Param("member") Member member);
}
