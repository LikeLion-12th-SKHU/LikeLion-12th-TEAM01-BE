package org.skhuton.fitpete.record.diet.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.diet.api.dto.DietDTO;
import org.skhuton.fitpete.record.diet.application.DietService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("daily/diet")
public class DietController {
    private final DietService dietService;

    @PostMapping
    @Operation(summary = "식단 기록", description = "식단 기록")
    public ResponseEntity<Void> saveDiet(@RequestBody DietDTO dietDTO) {
        dietService.saveDiet(dietDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
