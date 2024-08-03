package org.skhuton.fitpete.record.weight.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.skhuton.fitpete.auth.global.template.ResponseTemplate;
import org.skhuton.fitpete.record.weight.api.dto.WeightDTO;
import org.skhuton.fitpete.record.weight.application.WeightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/weight")
public class WeightController {

    private final WeightService weightService;

    public WeightController(WeightService weightService) {
        this.weightService = weightService;
    }

    @Operation(summary = "몸무게 기록 생성", description = "새로운 몸무게 기록을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "몸무게 기록 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(example = "BAD_REQUEST")))
    })
    @PostMapping
    public ResponseEntity<ResponseTemplate<WeightDTO>> createWeight(@RequestBody WeightDTO weightDTO, Principal principal) {
        Long memberId = getMemberIdFromPrincipal(principal);
        WeightDTO savedWeight = weightService.createWeight(memberId, weightDTO);

        ResponseTemplate<WeightDTO> response = new ResponseTemplate<>(
                HttpStatus.CREATED,
                "몸무게 기록이 성공적으로 생성되었습니다.",
                savedWeight
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "몸무게 기록 조회", description = "멤버의 몸무게 기록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "몸무게 기록 조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(example = "BAD_REQUEST")))
    })
    @GetMapping
    public ResponseEntity<ResponseTemplate<List<WeightDTO>>> getWeights(Principal principal) {
        Long memberId = getMemberIdFromPrincipal(principal);
        List<WeightDTO> weights = weightService.getWeights(memberId);

        ResponseTemplate<List<WeightDTO>> response = new ResponseTemplate<>(
                HttpStatus.OK,
                "몸무게 기록을 성공적으로 조회하였습니다.",
                weights
        );

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "몸무게 기록 수정", description = "기존 몸무게 기록을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "몸무게 기록 수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(example = "BAD_REQUEST")))
    })
    @PutMapping("/{weightId}")
    public ResponseEntity<ResponseTemplate<WeightDTO>> updateWeight(@PathVariable Long weightId, @RequestBody WeightDTO weightDTO) {
        WeightDTO updatedWeight = weightService.updateWeight(weightId, weightDTO);

        ResponseTemplate<WeightDTO> response = new ResponseTemplate<>(
                HttpStatus.OK,
                "몸무게 기록이 성공적으로 수정되었습니다.",
                updatedWeight
        );

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "몸무게 기록 삭제", description = "기존 몸무게 기록을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "몸무게 기록 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(example = "BAD_REQUEST")))
    })
    @DeleteMapping("/{weightId}")
    public ResponseEntity<ResponseTemplate<Void>> deleteWeight(@PathVariable Long weightId) {
        weightService.deleteWeight(weightId);

        ResponseTemplate<Void> response = new ResponseTemplate<>(
                HttpStatus.OK,
                "몸무게 기록이 성공적으로 삭제되었습니다."
        );

        return ResponseEntity.ok(response);
    }

    private Long getMemberIdFromPrincipal(Principal principal) {
        // Principal에서 멤버 ID를 추출하는 로직 (ex: 이메일로 멤버를 찾기)
        // 프로젝트의 인증 메커니즘에 따라 달라질 수 있음,,
        return 1L; // 예시!! (고정된 값을 반환)
    }
}
