package org.skhuton.fitpete.record.sleep.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.record.exercise.domain.ExerciseList;
import org.skhuton.fitpete.record.sleep.api.dto.SleepDTO;
import org.skhuton.fitpete.record.sleep.domain.Sleep;
import org.skhuton.fitpete.record.sleep.domain.repository.SleepRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.skhuton.fitpete.member.domain.QMember.member;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SleepService {
    private final SleepRepository sleepRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public SleepDTO createSleep(Long memberId, SleepDTO sleepDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new RuntimeException("멤버를 찾을 수 없습니다."));

        Sleep sleep = Sleep.builder()
                .member(member)
                .sleepHours(sleepDTO.sleepHours())
                .build();

        Sleep savedSleep = sleepRepository.save(sleep);

        member.incrementLevelCount();

        return new SleepDTO(
                savedSleep.getSleepId(),
                savedSleep.getSleepHours()
        );
    }

    @Transactional(readOnly = true)
    public List<SleepDTO> getSleeps(Long memberId) {
        return sleepRepository.findByMember_MemberId(memberId).stream()
                .map(sleep -> new SleepDTO(
                        sleep.getSleepId(),
                        sleep.getSleepHours()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public SleepDTO updateSleep(Long sleepId, SleepDTO sleepDTO) {
        Sleep sleep = sleepRepository.findById(sleepId)
                .orElseThrow(
                        () -> new RuntimeException("수면 기록을 찾을 수 없습니다."));

        sleep.setSleepHours(sleepDTO.sleepHours());

        Sleep updatedSleep = sleepRepository.save(sleep);

        Member member = sleep.getMember();
        member.incrementLevelCount();

        return new SleepDTO(
                updatedSleep.getSleepId(),
                updatedSleep.getSleepHours()
        );
    }

    @Transactional
    public void deleteSleep(Long sleepId) {
        Sleep sleep = sleepRepository.findById(sleepId)
                .orElseThrow(
                        () -> new RuntimeException("수면 기록을 찾을 수 없습니다."));

        Member member = sleep.getMember();
        sleepRepository.deleteById(sleepId);

        member.cancelLevelCount();
    }
}
