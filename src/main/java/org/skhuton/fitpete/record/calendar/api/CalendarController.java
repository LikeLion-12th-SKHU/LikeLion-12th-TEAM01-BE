package org.skhuton.fitpete.record.calendar.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.skhuton.fitpete.auth.global.template.ResponseTemplate;
import org.skhuton.fitpete.record.calendar.api.dto.CalendarDTO;
import org.skhuton.fitpete.record.calendar.api.dto.CalendarResponseDTO;
import org.skhuton.fitpete.record.calendar.api.dto.FindCalendarByDateDTO;
import org.skhuton.fitpete.record.calendar.application.CalendarService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @Operation(summary = "사용자의 캘린더 내역 출력", description = "사용자의 모든 날짜의 캘린더 내용을 출력합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "캘린더 데이터 출력 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 값"),
    })
    @GetMapping("")
    public List<CalendarResponseDTO> findByMemberIdCalendar(Authentication authentication) {
        return calendarService.findByMemberIdCalendar(authentication.getName());
    }

    @Operation(summary = "캘린더의 특정 날짜 데이터 출력", description = "특정 날짜의 내용을 출력합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "캘린더의 특정 날짜 데이터 출력 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 값"),
    })
    @GetMapping("/getCalendarDate")
    public FindCalendarByDateDTO findCalendarByDiaryDate(Authentication authentication,
                                                         @RequestParam String diaryDate) {
        return calendarService.findCalendarByDateInDietLogAndWorkoutList(authentication.getName(), diaryDate);
    }

    @Operation(summary = "특정 날짜의 캘린더 내용 업데이트", description = "특정 날짜의 캘린더 내용을 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "캘린더 데이터 입력 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 값"),
            @ApiResponse(responseCode = "401", description = "헤더 없음 or 토큰 불일치", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @PostMapping("/updateCalendar")
    public ResponseTemplate<String> updateCalendar(Authentication authentication,
                                                   @RequestBody CalendarDTO calendar) {
        calendarService.updateCalendar(authentication.getName(), calendar);
        return new ResponseTemplate<>(HttpStatus.OK, "캘린더 데이터 입력 성공");
    }
}
