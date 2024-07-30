package org.skhuton.fitpete.information.application;

import org.skhuton.fitpete.information.domain.Information;
import org.skhuton.fitpete.information.domain.repository.InformationRepository;
import org.skhuton.fitpete.information.api.dto.response.InformationInfoResponseDto;
import org.skhuton.fitpete.information.api.dto.response.InformationListResponseDto;
import org.skhuton.fitpete.information.exception.InformationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InformationService {

    private final InformationRepository informationRepository;

    @Autowired
    public InformationService(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    public InformationListResponseDto getAllInformation(Pageable pageable) {
        // 정보를 페이지네이션하여 가져오기
        Page<Information> informationPage = informationRepository.findAll(pageable);
        Page<InformationInfoResponseDto> dtoPage = informationPage.map(InformationInfoResponseDto::from);
        return InformationListResponseDto.from(dtoPage);
    }

    // 정보 검색(제목)
    public InformationListResponseDto searchInformationByTitle(String title, Pageable pageable) {
        Page<Information> informationPage = informationRepository.findByTitleContaining(title, pageable);
        Page<InformationInfoResponseDto> dtoPage = informationPage.map(InformationInfoResponseDto::from);
        return InformationListResponseDto.from(dtoPage);
    }

    // 정보 검색(내용)
    public InformationListResponseDto searchInformationByContent(String keyword, Pageable pageable) {
        Page<Information> informationPage = informationRepository.findByContentContaining(keyword, pageable);
        Page<InformationInfoResponseDto> dtoPage = informationPage.map(InformationInfoResponseDto::from);
        return InformationListResponseDto.from(dtoPage);
    }

    // 정보 검색(카테고리)
    public InformationListResponseDto categoryByInformationAll(String category, Pageable pageable) {
        Page<Information> informationPage;
        if (category.equals("ALL")) {
            informationPage = informationRepository.findAll(pageable);
        } else {
            informationPage = informationRepository.findByCategory(category, pageable);
        }

        Page<InformationInfoResponseDto> dtoPage = informationPage.map(InformationInfoResponseDto::from);
        return InformationListResponseDto.from(dtoPage);
    }

    // 정보 검색(카테고리 및 제목)
    public InformationListResponseDto searchInformationByCategoryAndTitle(String category, String title, Pageable pageable) {
        Page<Information> informationPage;

        if (category.equals("ALL")) {
            informationPage = informationRepository.findByTitleContaining(title, pageable);
        } else {
            informationPage = informationRepository.findByCategoryAndTitleContaining(category, title, pageable);
        }

        Page<InformationInfoResponseDto> dtoPage = informationPage.map(InformationInfoResponseDto::from);
        return InformationListResponseDto.from(dtoPage);
    }

    // 정보 상세
    public InformationInfoResponseDto getInformationById(Long informationId) {
        Information information = informationRepository.findByInformationId(informationId)
                .orElseThrow(() -> new InformationNotFoundException("정보글을 찾을 수 없습니다."));

        information.incrementViewCount();
        informationRepository.save(information);

        return InformationInfoResponseDto.from(information);
    }

    // 정보 삭제
    public void removeInformation(Long informationId) {
        informationRepository.deleteById(informationId);
    }
}