package org.skhuton.fitpete.record.menstrual.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.menstrual.api.dto.MenstrualDTO;
import org.skhuton.fitpete.record.menstrual.application.MenstrualService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("daily/menstrual")
public class MenstrualController {
    private final MenstrualService menstrualService;

    @PostMapping
    @Operation(summary = "생리 주기 기록", description = "생리 주기 기록")
    public ResponseEntity<MenstrualDTO> createMenstrual(
            @RequestParam Long memberId,
            @RequestBody MenstrualDTO menstrualDTO) {
        MenstrualDTO savedMenstrual = menstrualService.createMenstrual(memberId, menstrualDTO);
        return new ResponseEntity<>(savedMenstrual, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "생리 기록 조회", description = "생리 기록 조회")
    public ResponseEntity<List<MenstrualDTO>> getMenstruals(@RequestParam Long memberId) {
        List<MenstrualDTO> menstruals = menstrualService.getMenstruals(memberId);
        return new ResponseEntity<>(menstruals, HttpStatus.OK);
    }

    @PutMapping("/{menstrualId}")
    @Operation(summary = "생리 기록 수정", description = "생리 기록 수정")
    public ResponseEntity<MenstrualDTO> updateMenstrual(
            @PathVariable Long menstrualId,
            @RequestBody MenstrualDTO menstrualDTO) {
        MenstrualDTO updatedMenstrual = menstrualService.updateMenstrual(menstrualId, menstrualDTO);
        return new ResponseEntity<>(updatedMenstrual, HttpStatus.OK);
    }

    @DeleteMapping("/{menstrualId}")
    @Operation(summary = "생리 기록 삭제", description = "생리 기록 삭제")
    public ResponseEntity<Void> deleteMenstrual(@PathVariable Long menstrualId) {
        menstrualService.deleteMenstrual(menstrualId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
