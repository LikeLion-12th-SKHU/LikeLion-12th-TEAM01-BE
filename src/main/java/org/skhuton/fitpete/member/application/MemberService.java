package org.skhuton.fitpete.member.application;

import org.skhuton.fitpete.member.dto.OnboardingInfoUpdateRequestDto;
import org.skhuton.fitpete.member.exception.ExistsNicknameException;
import org.skhuton.fitpete.member.exception.MemberNotFoundException;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { this.memberRepository = memberRepository; }


    public boolean memberFirstLogin(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(email))
                .isFirstLogin();
    }

    public Member findMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(email));
    }



    // 닉네임 중복 검사
    public void validateDuplicateNickName(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new ExistsNicknameException(nickname);
        }
    }

    // 온보딩 정보 출력

}
