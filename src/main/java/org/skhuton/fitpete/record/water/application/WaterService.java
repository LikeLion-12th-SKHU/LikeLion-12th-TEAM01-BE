package org.skhuton.fitpete.record.water.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.record.water.api.dto.WaterDTO;
import org.skhuton.fitpete.record.water.domain.Water;
import org.skhuton.fitpete.record.water.domain.repository.WaterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WaterService {
    private final WaterRepository waterRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public WaterDTO createWater(Long memberId, WaterDTO waterDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));

        Water water = Water.builder()
                .member(member)
                .waterIntake(waterDTO.waterIntake())
                .build();

        Water savedWater = waterRepository.save(water);

        return new WaterDTO(
                savedWater.getWaterId(),
                savedWater.getWaterIntake()
        );
    }

    @Transactional(readOnly = true)
    public List<WaterDTO> getWaters(Long memberId) {
        return waterRepository.findByMember_MemberId(memberId).stream()
                .map(water -> new WaterDTO(
                        water.getWaterId(),
                        water.getWaterIntake()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public WaterDTO updateWater(Long waterId, WaterDTO waterDTO) {
        Water water = waterRepository.findById(waterId)
                .orElseThrow(
                        () -> new RuntimeException("물 섭취 기록을 찾을 수 없습니다.")
                );

        water.setWaterIntake(waterDTO.waterIntake());

        Water updatedWater = waterRepository.save(water);

        return new WaterDTO(
                updatedWater.getWaterId(),
                updatedWater.getWaterIntake()
        );
    }

    @Transactional
    public void deleteWater(Long waterId) {
        waterRepository.deleteById(waterId);
    }
}
