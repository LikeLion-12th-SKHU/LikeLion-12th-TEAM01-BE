package org.skhuton.fitpete.record.exercise.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.exercise.api.dto.ExerciseListDTO;
import org.skhuton.fitpete.record.exercise.domain.ExerciseList;
import org.skhuton.fitpete.record.exercise.domain.repository.ExerciseListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExerciseListService {
    private final ExerciseListRepository exerciseListRepository;

    @Transactional
    public void saveExerciseList(ExerciseListDTO exerciseListDTO) {
        ExerciseList exerciseList = ExerciseList.builder()
                .exerciseName(exerciseListDTO.exerciseName())
                .exerciseDuration(exerciseListDTO.exerciseDuration())
                .build();
        exerciseListRepository.save(exerciseList);
    }
}
