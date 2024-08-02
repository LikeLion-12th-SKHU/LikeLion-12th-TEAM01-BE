package org.skhuton.fitpete.information.application;

import org.skhuton.fitpete.auth.global.util.GlobalUtil;
import org.skhuton.fitpete.information.api.dto.response.InformationStoreInfoResponseDto;
import org.skhuton.fitpete.information.api.dto.response.InformationStoreListResponseDto;
import org.skhuton.fitpete.information.domain.Information;
import org.skhuton.fitpete.information.domain.InformationStore;
import org.skhuton.fitpete.information.domain.repository.InformationRepository;
import org.skhuton.fitpete.information.domain.repository.InformationStoreRepository;
import org.skhuton.fitpete.information.exception.ExistsInformationRecommendException;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.member.exception.MemberNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InformationStoreService {
    private final GlobalUtil globalUtil;
    private final InformationStoreRepository informationStoreRepository;
    private final MemberRepository memberRepository;
    private final InformationRepository informationRepository;

    public InformationStoreService(GlobalUtil globalUtil, InformationStoreRepository informationStoreRepository, MemberRepository memberRepository, InformationRepository informationRepository) {
        this.globalUtil = globalUtil;
        this.informationStoreRepository = informationStoreRepository;
        this.memberRepository = memberRepository;
        this.informationRepository = informationRepository;
    }

    // 정보글 저장 등록
    @Transactional
    public void addInformationStore(String email, Long informationId) {
        Member member = globalUtil.getMemberByEmail(email);
        Information information = globalUtil.getInformationById(informationId);

        if (informationStoreRepository.existsByInformationAndMember(information, member)) {
            throw new ExistsInformationRecommendException("이미 추천하는 정보글입니다.");
        }

        information.incrementStoreCount();

        informationStoreRepository.save(InformationStore.builder()
                .information(information)
                .member(member)
                .build());

        informationRepository.save(information);
    }

    // 저장한 정보글 조회
    public InformationStoreListResponseDto getInformationStore(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(email));

        List<InformationStore> storeList = informationStoreRepository.findInformationStoreByMember(member);

        List<InformationStoreInfoResponseDto> infoResponseDtos = storeList.stream()
                .map(stores -> new InformationStoreInfoResponseDto(member.getMemberId(), stores.getInformation()))
                .collect(Collectors.toList());
        return new InformationStoreListResponseDto(infoResponseDtos);
    }

    // 정보글 저장 취소
    @Transactional
    public void cancelInformationStore(String email, Long informationId) {
        Member member = globalUtil.getMemberByEmail(email);
        Information information = globalUtil.getInformationById(informationId);

        if (!informationStoreRepository.existsByInformationAndMember(information, member)) {
            throw new ExistsInformationRecommendException("추천하는 정보글이 없습니다.");
        }

        InformationStore informationStore = informationStoreRepository.findByInformationAndMember(information, member)
                .orElseThrow();

        information.cancelStoreCount();
        informationStoreRepository.delete(informationStore);
    }

}
