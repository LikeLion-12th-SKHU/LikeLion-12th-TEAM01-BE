package org.skhuton.fitpete.member.application;

import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.member.api.dto.request.OnboardingInfoUpdateRequestDto;
import org.skhuton.fitpete.member.exception.MemberNotFoundException;
import org.skhuton.fitpete.record.goal.domain.Goal;
import org.skhuton.fitpete.record.goal.domain.repository.GoalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyPageService {

    private final MemberRepository memberRepository;
    private final GoalRepository goalRepository;

    public MyPageService(MemberRepository memberRepository, GoalRepository goalRepository) {
        this.memberRepository = memberRepository;
        this.goalRepository = goalRepository;
    }

    // 사용자 이메일로 조회
    public Member findMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(email));
    }

    // 유저 정보 수정
    // 온보딩 정보 업데이트
    @Transactional
    public void onboardingInfoUpdate(String email, OnboardingInfoUpdateRequestDto onboardingInfoUpdateReqDto) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(email));
        Goal goal = Goal.builder()
                .goalWeigh(onboardingInfoUpdateReqDto.goalRequestDto().goalWeight())
                .goalWater(onboardingInfoUpdateReqDto.goalRequestDto().goalWater())
                .goalExerciseDuration(onboardingInfoUpdateReqDto.goalRequestDto().goalExerciseDuration())
                .goalExerciseRobbery(onboardingInfoUpdateReqDto.goalRequestDto().goalExerciseRobbery())
                .goalSleep(onboardingInfoUpdateReqDto.goalRequestDto().goalSleep())
                .member(member)
                .build();

        goalRepository.save(goal);

        member.firstLongUpdate();
        member.onboardingUpdate(onboardingInfoUpdateReqDto, goal);
    }

    // 회원탈퇴
    public void memberDeleteAccount(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(email));
        memberRepository.delete(member);
    }
}
