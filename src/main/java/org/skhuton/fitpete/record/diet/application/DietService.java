package org.skhuton.fitpete.record.diet.application;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.record.diet.api.dto.DietDTO;
import org.skhuton.fitpete.record.diet.domain.Diet;
import org.skhuton.fitpete.record.diet.domain.repository.DietRepository;
import org.skhuton.fitpete.record.diet.external.S3Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DietService {
    private final DietRepository dietRepository;
    private final MemberRepository memberRepository;
    private final S3Service s3Service;

    @Transactional
    public DietDTO createDiet(Long memberId, MultipartFile file, String foodDescription) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new RuntimeException("멤버를 찾을 수 없습니다."));

        String photoUrl = s3Service.uploadImage(file, "diet");

        Diet diet = Diet.builder()
                .member(member)
                .foodDescription(foodDescription)
                .photoUrl(photoUrl)
                .build();

        Diet savedDiet = dietRepository.save(diet);

        member.incrementLevelCount();

        return new DietDTO(savedDiet.getFoodDescription(), savedDiet.getPhotoUrl());
    }

    @Transactional(readOnly = true)
    public List<DietDTO> getDiets(Long memberId) {
        return dietRepository.findByMember_MemberId(memberId).stream()
                .map(diet -> new DietDTO(diet.getFoodDescription(), diet.getPhotoUrl()))
                .collect(Collectors.toList());
    }

    @Transactional
    public DietDTO updateDiet(Long dietId, MultipartFile file, String foodDescription) {
        Diet diet = dietRepository.findById(dietId)
                .orElseThrow(
                        () -> new RuntimeException("다이어트 기록을 찾을 수 없습니다."));

        if (file != null && !file.isEmpty()) {
            // 기존 파일 삭제
            s3Service.deleteFile(diet.getPhotoUrl());
            // 새로운 파일 업로드
            String newPhotoUrl = s3Service.uploadImage(file, "diet");
            diet.setPhotoUrl(newPhotoUrl);
        }

        diet.setFoodDescription(foodDescription);

        Diet updatedDiet = dietRepository.save(diet);

        return new DietDTO(updatedDiet.getFoodDescription(), updatedDiet.getPhotoUrl());
    }

    @Transactional
    public void deleteDiet(Long dietId) {
        Diet diet = dietRepository.findById(dietId)
                .orElseThrow(
                        () -> new RuntimeException("다이어트 기록을 찾을 수 없습니다."));

        s3Service.deleteFile(diet.getPhotoUrl());

        dietRepository.deleteById(dietId);

        Member member = diet.getMember();
        member.cancelLevelCount();
    }
}
