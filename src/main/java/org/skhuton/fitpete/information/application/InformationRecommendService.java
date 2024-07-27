package org.skhuton.fitpete.information.application;

import org.skhuton.fitpete.auth.global.util.GlobalUtil;
import org.skhuton.fitpete.information.domain.Information;
import org.skhuton.fitpete.information.domain.InformationRecommend;
import org.skhuton.fitpete.information.domain.repository.InformationRecommendRepository;
import org.skhuton.fitpete.information.exception.ExistsInformationRecommendException;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class InformationRecommendService {

    private final GlobalUtil globalUtil;
    private final InformationRecommendRepository informationRecommendRepository;

    public InformationRecommendService(GlobalUtil globalUtil, InformationRecommendRepository informationRecommendRepository) {
        this.globalUtil = globalUtil;
        this.informationRecommendRepository = informationRecommendRepository;
    }

    @Transactional
    public void addInformationRecommend(String email, Long informationId) {
        Member member = globalUtil.getMemberByEmail(email);
        Information information = globalUtil.getInformationById(informationId);

        if (informationRecommendRepository.existsByInformationAndMember(information, member)) {
            throw new ExistsInformationRecommendException("이미 추천하는 정보글입니다.");
        }

        information.updateRecommendCount();
        informationRecommendRepository.save(InformationRecommend.builder()
                .information(information)
                .member(member)
                .build());
    }

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


