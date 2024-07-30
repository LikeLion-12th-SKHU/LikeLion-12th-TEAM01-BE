package org.skhuton.fitpete.information.application;

import org.skhuton.fitpete.auth.global.util.GlobalUtil;
import org.skhuton.fitpete.information.api.dto.response.InformationRecommendInfoResponseDto;
import org.skhuton.fitpete.information.api.dto.response.InformationRecommendListResponseDto;
import org.skhuton.fitpete.information.domain.Information;
import org.skhuton.fitpete.information.domain.InformationRecommend;
import org.skhuton.fitpete.information.domain.repository.InformationRecommendRepository;
import org.skhuton.fitpete.information.domain.repository.InformationRepository;
import org.skhuton.fitpete.information.exception.ExistsInformationRecommendException;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.member.exception.MemberNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InformationRecommendService {

    private final GlobalUtil globalUtil;
    private final InformationRecommendRepository informationRecommendRepository;
    private final MemberRepository memberRepository;
    private final InformationRepository informationRepository;

    public InformationRecommendService(GlobalUtil globalUtil, InformationRecommendRepository informationRecommendRepository, MemberRepository memberRepository, InformationRepository informationRepository) {
        this.globalUtil = globalUtil;
        this.informationRecommendRepository = informationRecommendRepository;
        this.memberRepository = memberRepository;
        this.informationRepository = informationRepository;
    }

    // 정보글 추천
    @Transactional
    public void addInformationRecommend(String email, Long informationId) {
        Member member = globalUtil.getMemberByEmail(email);
        Information information = globalUtil.getInformationById(informationId);

        if (informationRecommendRepository.existsByInformationAndMember(information, member)) {
            throw new ExistsInformationRecommendException("이미 추천하는 정보글입니다.");
        }

        information.incrementRecommendCount();

        informationRecommendRepository.save(InformationRecommend.builder()
                .information(information)
                .member(member)
                .build());

        informationRepository.save(information);
    }

    // 추천한 정보글 조회
    @Transactional
    public InformationRecommendListResponseDto getInformationRecommend(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(email));

        List<InformationRecommend> recommendations = informationRecommendRepository.findInformationRecommendByMember(member);

        List<InformationRecommendInfoResponseDto> infoResponseDtos = recommendations.stream()
                .map(recommends -> new InformationRecommendInfoResponseDto(member.getMemberId(), recommends.getInformation()))
                .collect(Collectors.toList());
        return new InformationRecommendListResponseDto(infoResponseDtos);
    }

    // 정보글 추천 취소
    @Transactional
    public void cancelInformationRecommend(String email, Long informationId) {
        Member member = globalUtil.getMemberByEmail(email);
        Information information = globalUtil.getInformationById(informationId);

        if (!informationRecommendRepository.existsByInformationAndMember(information, member)) {
            throw new ExistsInformationRecommendException("추천하는 정보글이 없습니다.");
        }

        InformationRecommend informationRecommend = informationRecommendRepository.findByInformationAndMember(information, member)
                .orElseThrow();

        information.cancelRecommendCount();
        informationRecommendRepository.delete(informationRecommend);
    }
}


