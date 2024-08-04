package org.skhuton.fitpete.record.calendar.api.dto;

import lombok.Builder;
import org.skhuton.fitpete.record.diet.api.dto.DietDTO;
import org.skhuton.fitpete.record.exercise.api.dto.ExerciseListDTO;
import org.skhuton.fitpete.record.sleep.api.dto.SleepDTO;
import org.skhuton.fitpete.record.supplement.api.dto.SupplementDTO;
import org.skhuton.fitpete.record.water.api.dto.WaterDTO;
import org.skhuton.fitpete.record.menstrual.api.dto.MenstrualDTO;

import java.util.List;

@Builder
public record CalendarDTO(
        List<ExerciseListDTO> exerciseLists,
        List<WaterDTO> waterRecords,
        List<SupplementDTO> supplementRecords,
        List<DietDTO> dietRecords,
        List<SleepDTO> sleepRecords,
        List<MenstrualDTO> menstrualRecords,
        String diaryDate
) {
}
