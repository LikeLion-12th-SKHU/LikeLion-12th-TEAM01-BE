package org.skhuton.fitpete.information.domain.repository;

import org.skhuton.fitpete.information.domain.Information;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InformationRepository extends JpaRepository<Information, Long> {

    List<Information> findByTitleContaining(String title);
    List<Information> findByContentContaining(String keyword);

    Optional<Information> findByInformationId(Long informationId);

}
