package org.skhuton.fitpete.record.exercise.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.record.exercise.api.dto.ExerciseListDTO;
import org.skhuton.fitpete.record.exercise.domain.ExerciseList;
import org.skhuton.fitpete.record.exercise.domain.repository.ExerciseListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.skhuton.fitpete.member.domain.QMember.member;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExerciseListService {
    private final ExerciseListRepository exerciseListRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ExerciseListDTO createExerciseList(Long memberId, ExerciseListDTO exerciseListDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new RuntimeException("멤버를 찾을 수 없습니다."));

        ExerciseList exerciseList = ExerciseList.builder()
                .exerciseName(exerciseListDTO.exerciseName())
                .exerciseDuration(exerciseListDTO.exerciseDuration())
                .member(member)
                .build();

        ExerciseList savedExerciseList = exerciseListRepository.save(exerciseList);

        member.incrementLevelCount();

        return new ExerciseListDTO(
                savedExerciseList.getExercisreId(),
                savedExerciseList.getExerciseName(),
                savedExerciseList.getExerciseDuration()
        );
    }

    @Transactional(readOnly = true)
    public List<ExerciseListDTO> getExerciseLists(Long memberId) {
        return exerciseListRepository.findByMember_MemberId(memberId).stream()
                .map(exerciseList -> new ExerciseListDTO(
                        exerciseList.getExercisreId(),
                        exerciseList.getExerciseName(),
                        exerciseList.getExerciseDuration()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public ExerciseListDTO updateExerciseList(Long exerciseListId, ExerciseListDTO exerciseListDTO) {
        ExerciseList exerciseList = exerciseListRepository.findById(exerciseListId)
                .orElseThrow(
                        () -> new RuntimeException("운동 기록을 찾을 수 없습니다."));

        exerciseList.setExerciseName(exerciseListDTO.exerciseName());
        exerciseList.setExerciseDuration(exerciseListDTO.exerciseDuration());

        ExerciseList updatedExerciseList = exerciseListRepository.save(exerciseList);

        Member member = exerciseList.getMember();
        member.incrementLevelCount();


        return new ExerciseListDTO(
                updatedExerciseList.getExercisreId(),
                updatedExerciseList.getExerciseName(),
                updatedExerciseList.getExerciseDuration()
        );
    }

    @Transactional
    public void deleteExerciseList(Long exerciseListId) {
        ExerciseList exerciseList = exerciseListRepository.findById(exerciseListId)
                .orElseThrow(
                        () -> new RuntimeException("운동 기록을 찾을 수 없습니다."));

        Member member = exerciseList.getMember();
        exerciseListRepository.deleteById(exerciseListId);

        member.cancelLevelCount();
    }
}
