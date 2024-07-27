package org.skhuton.fitpete.member.application;

import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.member.dto.OnboardingInfoUpdateRequestDto;
import org.skhuton.fitpete.member.exception.MemberNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyPageService {

    private final MemberRepository memberRepository;


    public MyPageService(MemberRepository memberRepository) { this.memberRepository = memberRepository; }

    // 유저 정보 수정
    // 온보딩 정보 업데이트
    @Transactional
    public void onboardingInfoUpdate(String email, OnboardingInfoUpdateRequestDto onboardingInfoUpdateReqDto) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(email));

        member.firstLongUpdate();
        member.onboardingUpdate(onboardingInfoUpdateReqDto);
    }

    // 로그아웃


    // 회원탈퇴
    public void memberDeleteAccount(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(email));
        memberRepository.delete(member);
    }


}
