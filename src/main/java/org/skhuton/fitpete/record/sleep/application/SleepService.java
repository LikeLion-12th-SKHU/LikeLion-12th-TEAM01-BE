package org.skhuton.fitpete.record.sleep.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.record.sleep.api.dto.SleepDTO;
import org.skhuton.fitpete.record.sleep.api.dto.SleepCategory;
import org.skhuton.fitpete.record.sleep.domain.Sleep;
import org.skhuton.fitpete.record.sleep.domain.repository.SleepRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SleepService {
    private final SleepRepository sleepRepository;
    private final MemberRepository memberRepository;

    private SleepCategory determineSleepCategory(Double sleepHours) {
        if (sleepHours <= 6) {
            return SleepCategory.LESS_THAN_SIX_HOURS;
        } else if (sleepHours <= 8) {
            return SleepCategory.SEVEN_TO_EIGHT_HOURS;
        } else {
            return SleepCategory.MORE_THAN_NINE_HOURS;
        }
    }

    @Transactional
    public SleepDTO createSleep(Long memberId, SleepDTO sleepDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new RuntimeException("멤버를 찾을 수 없습니다."));

        Sleep sleep = Sleep.builder()
                .member(member)
                .sleepHours(sleepDTO.sleepHours())
                .sleepCategory(determineSleepCategory(sleepDTO.sleepHours()))  // 범주 결정
                .build();

        Sleep savedSleep = sleepRepository.save(sleep);

        member.incrementLevelCount();

        return new SleepDTO(
                savedSleep.getSleepId(),
                savedSleep.getSleepHours(),
                savedSleep.getSleepCategory()
        );
    }

    @Transactional(readOnly = true)
    public List<SleepDTO> getSleeps(Long memberId) {
        return sleepRepository.findByMember_MemberId(memberId).stream()
                .map(sleep -> new SleepDTO(
                        sleep.getSleepId(),
                        sleep.getSleepHours(),
                        sleep.getSleepCategory()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public SleepDTO updateSleep(Long sleepId, SleepDTO sleepDTO) {
        Sleep sleep = sleepRepository.findById(sleepId)
                .orElseThrow(
                        () -> new RuntimeException("수면 기록을 찾을 수 없습니다."));

        sleep.setSleepHours(sleepDTO.sleepHours());
        sleep.setSleepCategory(determineSleepCategory(sleepDTO.sleepHours()));  // 범주 업데이트

        Sleep updatedSleep = sleepRepository.save(sleep);

        Member member = sleep.getMember();
        member.incrementLevelCount();

        return new SleepDTO(
                updatedSleep.getSleepId(),
                updatedSleep.getSleepHours(),
                updatedSleep.getSleepCategory()
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
