package org.skhuton.fitpete.record.supplement.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.record.supplement.domain.SupplementCalendar;
import org.skhuton.fitpete.record.supplement.domain.SupplementType;
import org.skhuton.fitpete.record.calendar.domain.Calendar;
import org.skhuton.fitpete.record.calendar.domain.repository.CalendarRepository;
import org.skhuton.fitpete.record.supplement.domain.SupplementCalendarRepository;
import org.skhuton.fitpete.record.supplement.domain.SupplementTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SupplementService {
    private final SupplementCalendarRepository supplementCalendarRepository;
    private final SupplementTypeRepository supplementTypeRepository;
    private final CalendarRepository calendarRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveSupplement(Long memberId, Long calendarId, Long supplementTypeId, Boolean tookSupplements) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));
        Calendar calendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new RuntimeException("캘린더를 찾을 수 없습니다."));
        SupplementType supplementType = supplementTypeRepository.findById(supplementTypeId)
                .orElseThrow(() -> new RuntimeException("보충제 유형을 찾을 수 없습니다."));

        SupplementCalendar supplementCalendar = SupplementCalendar.builder()
                .member(member)
                .calendar(calendar)
                .supplementType(supplementType)
                .tookSupplements(tookSupplements)
                .build();

        supplementCalendarRepository.save(supplementCalendar);

        member.incrementLevelCount();
    }

    @Transactional
    public void updateSupplement(Long supplementId, Boolean tookSupplements) {
        SupplementCalendar supplementCalendar = supplementCalendarRepository.findById(supplementId)
                .orElseThrow(() -> new RuntimeException("보충제 섭취 기록을 찾을 수 없습니다."));

        supplementCalendar.setTookSupplements(tookSupplements);
        supplementCalendarRepository.save(supplementCalendar);

        Member member = supplementCalendar.getMember();
        member.incrementLevelCount();

    }

    @Transactional
    public void deleteSupplement(Long supplementId) {
        SupplementCalendar supplementCalendar = supplementCalendarRepository.findById(supplementId)
                .orElseThrow(() -> new RuntimeException("보충제 섭취 기록을 찾을 수 없습니다."));

        Member member = supplementCalendar.getMember();
        supplementCalendarRepository.deleteById(supplementId);
        member.cancelLevelCount();
    }

    @Transactional(readOnly = true)
    public List<SupplementCalendar> getSupplements(Long memberId) {
        return supplementCalendarRepository.findByMember_MemberId(memberId).stream()
                .collect(Collectors.toList());
    }
}
