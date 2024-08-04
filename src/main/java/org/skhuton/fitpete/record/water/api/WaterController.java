package org.skhuton.fitpete.record.water.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.water.api.dto.WaterDTO;
import org.skhuton.fitpete.record.water.application.WaterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("daily/water")
public class WaterController {
    private final WaterService waterService;

    @PostMapping
    @Operation(summary = "물 섭취 기록 저장", description = "물 섭취 기록을 저장합니다.")
    public ResponseEntity<WaterDTO> saveWater(
            @RequestParam Long memberId,
            @RequestBody WaterDTO waterDTO) {
        WaterDTO createdWater = waterService.createWater(memberId, waterDTO);
        return new ResponseEntity<>(createdWater, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "물 섭취 기록 조회", description = "회원의 물 섭취 기록을 조회합니다.")
    public ResponseEntity<List<WaterDTO>> getWaters(@RequestParam Long memberId) {
        List<WaterDTO> waters = waterService.getWaters(memberId);
        return new ResponseEntity<>(waters, HttpStatus.OK);
    }

    @PutMapping("/{waterId}")
    @Operation(summary = "물 섭취 기록 수정", description = "물 섭취 기록을 수정합니다.")
    public ResponseEntity<WaterDTO> updateWater(
            @PathVariable Long waterId,
            @RequestBody WaterDTO waterDTO) {
        WaterDTO updatedWater = waterService.updateWater(waterId, waterDTO);
        return new ResponseEntity<>(updatedWater, HttpStatus.OK);
    }

    @DeleteMapping("/{waterId}")
    @Operation(summary = "물 섭취 기록 삭제", description = "물 섭취 기록을 삭제합니다.")
    public ResponseEntity<Void> deleteWater(@PathVariable Long waterId) {
        waterService.deleteWater(waterId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
