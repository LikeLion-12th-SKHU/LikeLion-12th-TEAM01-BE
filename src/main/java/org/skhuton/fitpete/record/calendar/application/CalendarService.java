package org.skhuton.fitpete.record.calendar.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.member.exception.MemberNotFoundException;
import org.skhuton.fitpete.record.calendar.api.dto.CalendarDTO;
import org.skhuton.fitpete.record.calendar.api.dto.CalendarResponseDTO;
import org.skhuton.fitpete.record.calendar.api.dto.FindCalendarByDateDTO;
import org.skhuton.fitpete.record.calendar.api.dto.MainCalendarDTO;
import org.skhuton.fitpete.record.calendar.domain.Calendar;
import org.skhuton.fitpete.record.calendar.domain.repository.CalendarRepository;
import org.skhuton.fitpete.record.diet.domain.Diet;
import org.skhuton.fitpete.record.exercise.domain.ExerciseList;
import org.skhuton.fitpete.record.sleep.domain.Sleep;
import org.skhuton.fitpete.record.supplement.domain.Supplement;
import org.skhuton.fitpete.record.water.domain.Water;
import org.skhuton.fitpete.record.menstrual.domain.Menstrual;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public List<CalendarResponseDTO> findByMemberIdCalendar(String email) {
        log.info("findByMemberIdCalendar ");
        log.info("email = " + email);

        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberNotFoundException(email));
        List<Calendar> calendars = calendarRepository.findByMember(member);

        return calendars.stream()
                .map(c -> CalendarResponseDTO.builder()
                        .exerciseList(c.getExerciseLists().stream()
                                .map(ExerciseList::toDTO)
                                .findFirst()
                                .orElse(null))
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public FindCalendarByDateDTO findCalendarByDateInDietLogAndWorkoutList(String email, String diaryDate) {
        Calendar calendar = findCalendarByDiaryDate(email, diaryDate);

        return FindCalendarByDateDTO.builder()
                .waterRecords(calendar.getWaterRecords().stream()
                        .map(Water::toDTO)
                        .collect(Collectors.toList()))
                .supplementRecords(calendar.getSupplementRecords().stream()
                        .map(Supplement::toDTO)
                        .collect(Collectors.toList()))
                .dietRecords(calendar.getDietRecords().stream()
                        .map(Diet::toDTO)
                        .collect(Collectors.toList()))
                .exerciseLists(calendar.getExerciseLists().stream()
                        .map(ExerciseList::toDTO)
                        .collect(Collectors.toList()))
                .sleepRecords(calendar.getSleepRecords().stream()
                        .map(Sleep::toDTO)
                        .collect(Collectors.toList()))
                .menstrualRecords(calendar.getMenstrualRecords().stream()
                        .map(Menstrual::toDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    @Transactional
    public Calendar findCalendarByDiaryDate(String email, String diaryDate) {
        log.info("findCalendarByDiaryDate ");
        log.info("email = " + email);
        log.info("diaryDate = " + diaryDate);
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberNotFoundException(email));

        return calendarRepository.findByMemberAndDiaryDate(member, diaryDate);
    }

    @Transactional
    public void updateCalendar(String email, CalendarDTO calendarDTO) {
        log.info("updateCalendar ");
        log.info("email = " + email);
        log.info("calendarDTO = " + calendarDTO.toString());

        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberNotFoundException(email));

        Calendar calendar = calendarRepository.findByMemberAndDiaryDate(member, calendarDTO.diaryDate());

        if (calendar == null) {
            calendar = Calendar.builder()
                    .member(member)
                    .build();
        }

        calendarRepository.save(calendar);
    }
}
