package org.skhuton.fitpete.record.calendar.domain.repository;

import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.record.calendar.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findByMember(Member member);

    Calendar findByMemberAndDiaryDate(Member member, String diaryDate);

    Boolean existsCalendarByMemberAndDiaryDate(Member member, String diaryDate);
}
