package org.skhuton.fitpete.information.domain.repository;

import org.skhuton.fitpete.information.domain.Information;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InformationRepository extends JpaRepository<Information, Long> {

    Optional<Information> findByInformationId(Long informationId);
    Page<Information> findByTitleContaining(String title, Pageable pageable);
    Page<Information> findByContentContaining(String keyword, Pageable pageable);
    Page<Information> findAll(Pageable pageable);
    Page<Information> findByCategory(String category, Pageable pageable);
    Page<Information> findByCategoryAndTitleContaining(@Param("category") String category,
                                                       @Param("title") String title,
                                                       Pageable pageable);
}
