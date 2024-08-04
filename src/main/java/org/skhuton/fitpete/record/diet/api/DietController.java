package org.skhuton.fitpete.record.diet.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.diet.api.dto.DietDTO;
import org.skhuton.fitpete.record.diet.application.DietService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("daily/diet")
public class DietController {
    private final DietService dietService;

    @PostMapping
    @Operation(summary = "식단 기록 저장", description = "식단 기록을 저장합니다.")
    public ResponseEntity<DietDTO> saveDiet(
            @RequestParam Long memberId,
            @RequestBody DietDTO dietDTO) {
        DietDTO createdDiet = dietService.createDiet(memberId, dietDTO);
        return new ResponseEntity<>(createdDiet, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "식단 기록 조회", description = "회원의 식단 기록을 조회합니다.")
    public ResponseEntity<List<DietDTO>> getDiets(@RequestParam Long memberId) {
        List<DietDTO> diets = dietService.getDiets(memberId);
        return new ResponseEntity<>(diets, HttpStatus.OK);
    }

    @PutMapping("/{dietId}")
    @Operation(summary = "식단 기록 수정", description = "식단 기록을 수정합니다.")
    public ResponseEntity<DietDTO> updateDiet(
            @PathVariable Long dietId,
            @RequestBody DietDTO dietDTO) {
        DietDTO updatedDiet = dietService.updateDiet(dietId, dietDTO);
        return new ResponseEntity<>(updatedDiet, HttpStatus.OK);
    }

    @DeleteMapping("/{dietId}")
    @Operation(summary = "식단 기록 삭제", description = "식단 기록을 삭제합니다.")
    public ResponseEntity<Void> deleteDiet(@PathVariable Long dietId) {
        dietService.deleteDiet(dietId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
