package org.skhuton.fitpete.record.sleep.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.sleep.api.dto.SleepDTO;
import org.skhuton.fitpete.record.sleep.application.SleepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("daily/sleep")
public class SleepController {
    private final SleepService sleepService;

    @PostMapping
    @Operation(summary = "수면 시간 기록", description = "새로운 수면 시간 기록을 생성합니다.")
    public ResponseEntity<SleepDTO> createSleep(
            @RequestParam Long memberId,
            @RequestBody SleepDTO sleepDTO) {
        SleepDTO createdSleep = sleepService.createSleep(memberId, sleepDTO);
        return new ResponseEntity<>(createdSleep, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "수면 기록 조회", description = "특정 멤버의 수면 기록을 조회합니다.")
    public ResponseEntity<List<SleepDTO>> getSleeps(@RequestParam Long memberId) {
        List<SleepDTO> sleeps = sleepService.getSleeps(memberId);
        return new ResponseEntity<>(sleeps, HttpStatus.OK);
    }

    @PutMapping("/{sleepId}")
    @Operation(summary = "수면 기록 수정", description = "특정 수면 기록을 수정합니다.")
    public ResponseEntity<SleepDTO> updateSleep(
            @PathVariable Long sleepId,
            @RequestBody SleepDTO sleepDTO) {
        SleepDTO updatedSleep = sleepService.updateSleep(sleepId, sleepDTO);
        return new ResponseEntity<>(updatedSleep, HttpStatus.OK);
    }

    @DeleteMapping("/{sleepId}")
    @Operation(summary = "수면 기록 삭제", description = "특정 수면 기록을 삭제합니다.")
    public ResponseEntity<Void> deleteSleep(@PathVariable Long sleepId) {
        sleepService.deleteSleep(sleepId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
