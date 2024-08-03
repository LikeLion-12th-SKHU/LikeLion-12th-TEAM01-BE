package org.skhuton.fitpete.record.exercise.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.exercise.api.dto.ExerciseListDTO;
import org.skhuton.fitpete.record.exercise.application.ExerciseListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("daily/exercise")
public class ExerciseListController {
    private final ExerciseListService exerciseListService;

    @PostMapping
    @Operation(summary = "운동 기록 저장", description = "운동 기록을 저장합니다.")
    public ResponseEntity<ExerciseListDTO> saveExerciseList(
            @RequestParam Long memberId,
            @RequestBody ExerciseListDTO exerciseListDTO) {
        ExerciseListDTO createdExerciseList = exerciseListService.createExerciseList(memberId, exerciseListDTO);
        return new ResponseEntity<>(createdExerciseList, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "운동 기록 조회", description = "회원의 운동 기록을 조회합니다.")
    public ResponseEntity<List<ExerciseListDTO>> getExerciseLists(@RequestParam Long memberId) {
        List<ExerciseListDTO> exerciseLists = exerciseListService.getExerciseLists(memberId);
        return new ResponseEntity<>(exerciseLists, HttpStatus.OK);
    }

    @PutMapping("/{exerciseListId}")
    @Operation(summary = "운동 기록 수정", description = "운동 기록을 수정합니다.")
    public ResponseEntity<ExerciseListDTO> updateExerciseList(
            @PathVariable Long exerciseListId,
            @RequestBody ExerciseListDTO exerciseListDTO) {
        ExerciseListDTO updatedExerciseList = exerciseListService.updateExerciseList(exerciseListId, exerciseListDTO);
        return new ResponseEntity<>(updatedExerciseList, HttpStatus.OK);
    }

    @DeleteMapping("/{exerciseListId}")
    @Operation(summary = "운동 기록 삭제", description = "운동 기록을 삭제합니다.")
    public ResponseEntity<Void> deleteExerciseList(@PathVariable Long exerciseListId) {
        exerciseListService.deleteExerciseList(exerciseListId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
