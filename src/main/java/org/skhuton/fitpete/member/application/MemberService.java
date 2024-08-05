package org.skhuton.fitpete.member.application;

import org.skhuton.fitpete.member.exception.ExistsNicknameException;
import org.skhuton.fitpete.member.exception.MemberNotFoundException;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { this.memberRepository = memberRepository; }


    public boolean memberFirstLogin(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(email))
                .isFirstLogin();
    }

    // 닉네임 중복 검사
    public void validateDuplicateNickName(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new ExistsNicknameException(nickname);
        }
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("해당 이메일의 멤버를 찾을 수 없습니다: " + email));
    }

    // 전체 사용자 랭킹 조회
    @Transactional(readOnly = true)
    public List<Member> getAllMembersRank() {
        List<Member> members = memberRepository.findAll();
        return Member.getMembersByRank(members); // Member의 정적 메서드 호출
    }

}
