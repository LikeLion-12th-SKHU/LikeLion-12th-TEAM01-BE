package org.skhuton.fitpete.information.application;

import org.skhuton.fitpete.information.domain.Information;
import org.skhuton.fitpete.information.domain.repository.InformationRepository;
import org.skhuton.fitpete.information.exception.InformationNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformationService {

    private final InformationRepository informationRepository;
    public InformationService(InformationRepository informationRepository) { this.informationRepository = informationRepository; }

    // 정보 목록 페이지
    public List<Information> getAllInformation() {
        return informationRepository.findAll();
    }

    // 정보 검색(제목)
    public List<Information> searchInformationByTitle(String title) {
        return informationRepository.findByTitleContaining(title);
    }

    // 정보 검색(내용)
    public List<Information> searchInformationByContent(String keyword) {
        return informationRepository.findByContentContaining(keyword);
    }

    // 정보 상세
    public Information getInformationById(Long informationId) {

        return informationRepository.findById(informationId)
                .orElseThrow(() -> new InformationNotFoundException("정보글을 찾을 수 없습니다."));
    }

    // 정보 삭제
    public void removeInformation(Long informationId) {
        informationRepository.deleteById(informationId);
    }

}
