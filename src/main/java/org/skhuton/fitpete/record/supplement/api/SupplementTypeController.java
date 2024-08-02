package org.skhuton.fitpete.record.supplement.api;

import lombok.RequiredArgsConstructor;
import org.skhuton.fitpete.record.supplement.application.SupplementTypeService;
import org.skhuton.fitpete.record.supplement.api.dto.SupplementTypeDTO;
import org.skhuton.fitpete.record.supplement.domain.SupplementType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplement-types")
@RequiredArgsConstructor
public class SupplementTypeController {
    private final SupplementTypeService supplementTypeService;

    @PostMapping
    public ResponseEntity<SupplementTypeDTO> addSupplementType(@RequestBody String name) {
        SupplementType supplementType = supplementTypeService.addSupplementType(name);
        return new ResponseEntity<>(supplementType.toDTO(), HttpStatus.CREATED);
    }
}
