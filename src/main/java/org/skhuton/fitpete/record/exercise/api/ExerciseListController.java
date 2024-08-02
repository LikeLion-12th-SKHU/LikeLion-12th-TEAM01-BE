package org.skhuton.fitpete.record.exercise.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.exercise.api.dto.ExerciseListDTO;
import org.skhuton.fitpete.record.exercise.application.ExerciseListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("daily/exercise")
public class ExerciseListController {
    private final ExerciseListService exerciseListService;

    @PostMapping
    @Operation(summary = "운동 기록", description = "운동 기록")
    public ResponseEntity<Void> saveExerciseList(@RequestBody ExerciseListDTO exerciseListDTO) {
        exerciseListService.saveExerciseList(exerciseListDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
