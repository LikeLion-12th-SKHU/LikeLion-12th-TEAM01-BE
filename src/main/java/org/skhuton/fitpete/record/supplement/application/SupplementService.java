package org.skhuton.fitpete.record.supplement.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.member.api.dto.response.OnboardingResponseDto;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.member.exception.MemberNotFoundException;
import org.skhuton.fitpete.record.supplement.domain.*;
import org.skhuton.fitpete.record.calendar.domain.Calendar;
import org.skhuton.fitpete.record.calendar.domain.repository.CalendarRepository;
import org.skhuton.fitpete.record.supplement.domain.repository.SupplementTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
                .orElseThrow(
                        () -> new RuntimeException("멤버를 찾을 수 없습니다.")
                );
        Calendar calendar = calendarRepository.findById(calendarId)
                .orElseThrow(
                        () -> new RuntimeException("캘린더를 찾을 수 없습니다.")
                );
        SupplementType supplementType = supplementTypeRepository.findById(supplementTypeId)
                .orElseThrow(
                        () -> new RuntimeException("보충제 유형을 찾을 수 없습니다.")
                );

        SupplementCalendar supplementCalendar = SupplementCalendar.builder()
                .member(member)
                .calendar(calendar)
                .supplementType(supplementType)
                .tookSupplements(tookSupplements)
                .build();

        supplementCalendarRepository.save(supplementCalendar);
    }

    public OnboardingResponseDto convertSupplementStringToList(String email, OnboardingResponseDto.OnboardingResponseDtoBuilder builder) {
        Member member = memberRepository.findByEmail(email)
                        .orElseThrow(() -> new MemberNotFoundException("멤버를 찾을 수 없습니다."));

        String[] ids = member.getSupplementList().split(",");
        List<Long> supplementIdList = new ArrayList<>();

        for (String id : ids) {
            Long value = Long.parseLong(id.trim());
            supplementIdList.add(value);
        }

        return builder.supplementList(supplementTypeRepository.findBySupplementTypeIdIn(supplementIdList)).build(); // 값으로 찾기

    }
}