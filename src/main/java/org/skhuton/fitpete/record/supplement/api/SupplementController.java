package org.skhuton.fitpete.record.supplement.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.supplement.api.dto.SupplementDTO;
import org.skhuton.fitpete.record.supplement.application.SupplementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("daily/supplement")
public class SupplementController {
    private final SupplementService supplementService;

    @PostMapping
    @Operation(summary = "섭취 영양제 기록", description = "섭취 영양제 기록")
    public ResponseEntity<Void> saveSupplement(@RequestBody SupplementDTO supplementDTO) {
        supplementService.saveSupplement(supplementDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
