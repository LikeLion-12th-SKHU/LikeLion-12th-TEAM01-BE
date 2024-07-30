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

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public TeamService(TeamRepository teamRepository, MemberRepository memberRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
    }

    // 팀 생성
    @Transactional
//    public Team createTeam(Team team) {
//        if (teamRepository.existsByTeamName(team.getTeamName())) {
//            throw new TeamAlreadyExistsException("이미 존재하는 팀 이름입니다: " + team.getTeamName());
//        }
//        return teamRepository.save(team);
//    }
    public Team createTeam(TeamSaveRequestDto teamSaveRequestDto, Member member) {
        if (teamRepository.existsByTeamName(teamSaveRequestDto.teamName())) {
            throw new TeamAlreadyExistsException("이미 존재하는 팀 이름입니다: " + teamSaveRequestDto.teamName());
        }

        Team team = teamSaveRequestDto.toEntity(member);
        return teamRepository.save(team);
    }

    // 팀 멤버 추가
    public Member addMember(Long teamId, MemberSaveRequestDto memberSaveRequestDto) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException(teamId));

        Member member = memberSaveRequestDto.toEntity();
        member.setTeam(team);

        return memberRepository.save(member);
    }

    // memberId로 팀 조회
    public TeamListResponseDto getTeamsByMemberId(Long memberId) {
        return memberRepository.findTeamByMemberId(memberId);
    }

    // 팀 멤버 삭제
    public void removeMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

}
