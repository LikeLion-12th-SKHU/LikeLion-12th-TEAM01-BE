package org.skhuton.fitpete.team.application;

import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.member.api.dto.request.MemberSaveRequestDto;
import org.skhuton.fitpete.team.api.dto.request.TeamSaveRequestDto;
import org.skhuton.fitpete.team.api.dto.response.TeamListResponseDto;
import org.skhuton.fitpete.team.domain.Team;
import org.skhuton.fitpete.team.domain.repository.TeamRepository;
import org.skhuton.fitpete.team.exception.TeamAlreadyExistsException;
import org.skhuton.fitpete.team.exception.TeamNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public TeamService(TeamRepository teamRepository, MemberRepository memberRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Team createTeam(TeamSaveRequestDto teamSaveRequestDto, Member member) {
        if (teamRepository.existsByTeamName(teamSaveRequestDto.teamName())) {
            throw new TeamAlreadyExistsException("이미 존재하는 팀 이름입니다: " + teamSaveRequestDto.teamName());
        }

        Team team = teamSaveRequestDto.toEntity(member);
        return teamRepository.save(team);
    }

    public Member addMember(Long teamId, MemberSaveRequestDto memberSaveRequestDto) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException(teamId));

        Member member = memberSaveRequestDto.toEntity();
        team.addMember(member);

        return memberRepository.save(member);
    }

    public TeamListResponseDto getTeamsByMemberId(Long memberId) {
        return memberRepository.findTeamByMemberId(memberId);
    }

    public void removeMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    // 팀 내 멤버 순위 조회
    @Transactional(readOnly = true)
    public List<Member> getMembersByTeamIdAndRank(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException(teamId));
        List<Member> members = team.getMembers();
        return Member.getMembersByRank(members);
    }
}
