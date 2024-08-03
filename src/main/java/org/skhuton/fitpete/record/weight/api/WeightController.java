package org.skhuton.fitpete.record.weight.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.weight.api.dto.WeightDTO;
import org.skhuton.fitpete.record.weight.application.WeightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weight")
public class WeightController {

    private final WeightService weightService;

    @PostMapping
    @Operation(summary = "몸무게 기록 생성", description = "새로운 몸무게 기록을 생성합니다.")
    public ResponseEntity<WeightDTO> createWeight(
            @RequestParam Long memberId,
            @RequestBody WeightDTO weightDTO) {
        WeightDTO savedWeight = weightService.createWeight(memberId, weightDTO);
        return new ResponseEntity<>(savedWeight, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "몸무게 기록 조회", description = "멤버의 몸무게 기록을 조회합니다.")
    public ResponseEntity<List<WeightDTO>> getWeights(@RequestParam Long memberId) {
        List<WeightDTO> weights = weightService.getWeights(memberId);
        return new ResponseEntity<>(weights, HttpStatus.OK);
    }

    @PutMapping("/{weightId}")
    @Operation(summary = "몸무게 기록 수정", description = "기존 몸무게 기록을 수정합니다.")
    public ResponseEntity<WeightDTO> updateWeight(
            @PathVariable Long weightId,
            @RequestBody WeightDTO weightDTO) {
        WeightDTO updatedWeight = weightService.updateWeight(weightId, weightDTO);
        return new ResponseEntity<>(updatedWeight, HttpStatus.OK);
    }

    @DeleteMapping("/{weightId}")
    @Operation(summary = "몸무게 기록 삭제", description = "기존 몸무게 기록을 삭제합니다.")
    public ResponseEntity<Void> deleteWeight(@PathVariable Long weightId) {
        weightService.deleteWeight(weightId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
