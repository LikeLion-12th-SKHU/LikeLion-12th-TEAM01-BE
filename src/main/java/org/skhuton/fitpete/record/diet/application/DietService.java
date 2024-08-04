package org.skhuton.fitpete.record.diet.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.record.diet.api.dto.DietDTO;
import org.skhuton.fitpete.record.diet.domain.Diet;
import org.skhuton.fitpete.record.diet.domain.repository.DietRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DietService {
    private final DietRepository dietRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public DietDTO createDiet(Long memberId, DietDTO dietDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));

        Diet diet = Diet.builder()
                .member(member)
                .foodDescription(dietDTO.foodDescription())
                .photoUrl(dietDTO.photoUrl())
                .build();

        Diet savedDiet = dietRepository.save(diet);

        return new DietDTO(
                savedDiet.getFoodDescription(),
                savedDiet.getPhotoUrl()
        );
    }

    @Transactional(readOnly = true)
    public List<DietDTO> getDiets(Long memberId) {
        return dietRepository.findByMember_MemberId(memberId).stream()
                .map(diet -> new DietDTO(
                        diet.getFoodDescription(),
                        diet.getPhotoUrl()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public DietDTO updateDiet(Long dietId, DietDTO dietDTO) {
        Diet diet = dietRepository.findById(dietId)
                .orElseThrow(() -> new RuntimeException("다이어트 기록을 찾을 수 없습니다."));

        diet.setFoodDescription(dietDTO.foodDescription());
        diet.setPhotoUrl(dietDTO.photoUrl());

        Diet updatedDiet = dietRepository.save(diet);

        return new DietDTO(
                updatedDiet.getFoodDescription(),
                updatedDiet.getPhotoUrl()
        );
    }

    @Transactional
    public void deleteDiet(Long dietId) {
        dietRepository.deleteById(dietId);
    }
}
