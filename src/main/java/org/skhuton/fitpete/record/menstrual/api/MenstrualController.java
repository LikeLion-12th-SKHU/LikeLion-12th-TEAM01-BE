package org.skhuton.fitpete.record.menstrual.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.menstrual.api.dto.MenstrualDTO;
import org.skhuton.fitpete.record.menstrual.application.MenstrualService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("daily/menstrual")
public class MenstrualController {
    private final MenstrualService menstrualService;

    @PostMapping
    @Operation(summary = "생리 주기 기록", description = "생리 주기 기록")
    public ResponseEntity<Void> saveMenstrual(@RequestBody MenstrualDTO menstrualDTO) {
        menstrualService.saveMenstrual(menstrualDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
