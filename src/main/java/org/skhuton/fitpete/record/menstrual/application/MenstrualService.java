package org.skhuton.fitpete.record.menstrual.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.record.exercise.domain.ExerciseList;
import org.skhuton.fitpete.record.menstrual.api.dto.MenstrualDTO;
import org.skhuton.fitpete.record.menstrual.domain.Menstrual;
import org.skhuton.fitpete.record.menstrual.domain.repository.MenstrualRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenstrualService {
    private final MenstrualRepository menstrualRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MenstrualDTO createMenstrual(Long memberId, MenstrualDTO menstrualDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));

        Menstrual menstrual = Menstrual.builder()
                .member(member)
                .menstrualCycle(menstrualDTO.menstrualCycle())
                .menstrualStartDate(menstrualDTO.menstrualStartDate())
                .menstrualEndDate(menstrualDTO.menstrualEndDate())
                .build();

        Menstrual savedMenstrual = menstrualRepository.save(menstrual);

        member.incrementLevelCount();

        return new MenstrualDTO(
                savedMenstrual.getMenstrualId(),
                savedMenstrual.getMenstrualCycle(),
                savedMenstrual.getMenstrualStartDate(),
                savedMenstrual.getMenstrualEndDate()
        );
    }

    @Transactional(readOnly = true)
    public List<MenstrualDTO> getMenstruals(Long memberId) {
        return menstrualRepository.findByMember_MemberId(memberId).stream()
                .map(menstrual -> new MenstrualDTO(
                        menstrual.getMenstrualId(),
                        menstrual.getMenstrualCycle(),
                        menstrual.getMenstrualStartDate(),
                        menstrual.getMenstrualEndDate()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public MenstrualDTO updateMenstrual(Long menstrualId, MenstrualDTO menstrualDTO) {
        Menstrual menstrual = menstrualRepository.findById(menstrualId)
                .orElseThrow(
                        () -> new RuntimeException("생리 기록을 찾을 수 없습니다."));

        menstrual.setMenstrualCycle(menstrualDTO.menstrualCycle());
        menstrual.setMenstrualStartDate(menstrualDTO.menstrualStartDate());
        menstrual.setMenstrualEndDate(menstrualDTO.menstrualEndDate());

        Menstrual updatedMenstrual = menstrualRepository.save(menstrual);

        Member member = menstrual.getMember();
        member.incrementLevelCount();

        return new MenstrualDTO(
                updatedMenstrual.getMenstrualId(),
                updatedMenstrual.getMenstrualCycle(),
                updatedMenstrual.getMenstrualStartDate(),
                updatedMenstrual.getMenstrualEndDate()
        );
    }

    @Transactional
    public void deleteMenstrual(Long menstrualId) {
        Menstrual menstrual = menstrualRepository.findById(menstrualId)
                .orElseThrow(
                        () -> new RuntimeException("생리 기록을 찾을 수 없습니다."));

        Member member = menstrual.getMember();
        menstrualRepository.deleteById(menstrualId);

        member.cancelLevelCount();
    }
}
