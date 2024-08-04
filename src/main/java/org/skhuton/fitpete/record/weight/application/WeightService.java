package org.skhuton.fitpete.record.weight.application;

import org.skhuton.fitpete.member.domain.Member;
import org.skhuton.fitpete.member.domain.repository.MemberRepository;
import org.skhuton.fitpete.record.weight.api.dto.WeightDTO;
import org.skhuton.fitpete.record.weight.domain.Weight;
import org.skhuton.fitpete.record.weight.domain.repository.WeightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeightService {

    private final WeightRepository weightRepository;
    private final MemberRepository memberRepository;

    public WeightService(WeightRepository weightRecordRepository, MemberRepository memberRepository) {
        this.weightRepository = weightRecordRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public WeightDTO createWeight(Long memberId, WeightDTO weightDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new RuntimeException("멤버를 찾을 수 없습니다.")
                );

        Weight weight = Weight.builder()
                .member(member)
                .weight(weightDTO.weight())
                .recordedAt(weightDTO.recordedAt())
                .build();

        Weight savedWeight = weightRepository.save(weight);

        return new WeightDTO(
                savedWeight.getWeightId(),
                savedWeight.getWeight(),
                savedWeight.getRecordedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<WeightDTO> getWeights(Long memberId) {
        return weightRepository.findByMember_MemberId(memberId).stream()
                .map(record -> new WeightDTO(
                        record.getWeightId(),
                        record.getWeight(),
                        record.getRecordedAt()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public WeightDTO updateWeight(Long weightId, WeightDTO weightDTO) {
        Weight weight = weightRepository.findById(weightId)
                .orElseThrow(
                        () -> new RuntimeException("몸무게 기록을 찾을 수 없습니다.")
                );

        weight.setWeight(weightDTO.weight());
        weight.setRecordedAt(weightDTO.recordedAt());

        Weight updatedWeight = weightRepository.save(weight);

        return new WeightDTO(
                updatedWeight.getWeightId(),
                updatedWeight.getWeight(),
                updatedWeight.getRecordedAt()
        );
    }

    @Transactional
    public void deleteWeight(Long weightId) {
        weightRepository.deleteById(weightId);
    }
}
