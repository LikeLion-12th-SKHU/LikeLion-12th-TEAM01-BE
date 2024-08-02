package org.skhuton.fitpete.record.water.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.water.api.dto.WaterDTO;
import org.skhuton.fitpete.record.water.application.WaterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("daily/water")
public class WaterController {
    private final WaterService waterService;

    @PostMapping
    @Operation(summary = "섭취한 물 기록", description = "섭취한 물 기록")
    public ResponseEntity<Void> saveWater(@RequestBody WaterDTO waterDTO) {
        waterService.saveWater(waterDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
