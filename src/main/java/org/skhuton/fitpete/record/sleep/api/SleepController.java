package org.skhuton.fitpete.record.sleep.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.sleep.api.dto.SleepDTO;
import org.skhuton.fitpete.record.sleep.application.SleepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("daily/sleep")
public class SleepController {
    private final SleepService sleepService;

    @PostMapping
    @Operation(summary = "수면 시간 기록", description = "수면 시간 기록")
    public ResponseEntity<Void> saveSleep(@RequestBody SleepDTO sleepDTO) {
        sleepService.saveSleep(sleepDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
