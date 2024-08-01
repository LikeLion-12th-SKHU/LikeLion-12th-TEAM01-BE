package org.skhuton.fitpete.record.calendar.api.dto;

import lombok.*;
import org.skhuton.fitpete.record.diet.api.dto.DietDTO;
import org.skhuton.fitpete.record.exercise.api.dto.ExerciseListDTO;
import org.skhuton.fitpete.record.sleep.api.dto.SleepDTO;
import org.skhuton.fitpete.record.supplement.api.dto.SupplementDTO;
import org.skhuton.fitpete.record.water.api.dto.WaterDTO;
import org.skhuton.fitpete.record.menstrual.api.dto.MenstrualDTO;

import java.util.List;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindCalendarByDateDTO {
    private List<WaterDTO> waterRecords;
    private List<SupplementDTO> supplementRecords;
    private List<DietDTO> dietRecords;
    private List<ExerciseListDTO> exerciseLists;
    private List<SleepDTO> sleepRecords;
    private List<MenstrualDTO> menstrualRecords;
}
