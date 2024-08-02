package org.skhuton.fitpete.record.supplement.config;

import org.skhuton.fitpete.record.supplement.domain.SupplementType;
import org.skhuton.fitpete.record.supplement.domain.SupplementTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(SupplementTypeRepository supplementTypeRepository) {
        return args -> {
            supplementTypeRepository.save(new SupplementType(null, "비타민D"));
            supplementTypeRepository.save(new SupplementType(null, "비타민C"));
            supplementTypeRepository.save(new SupplementType(null, "칼슘"));
            supplementTypeRepository.save(new SupplementType(null, "오메가3"));
            supplementTypeRepository.save(new SupplementType(null, "마그네슘"));
            supplementTypeRepository.save(new SupplementType(null, "종합비타민"));
            supplementTypeRepository.save(new SupplementType(null, "유산균"));
            supplementTypeRepository.save(new SupplementType(null, "철분제"));
            supplementTypeRepository.save(new SupplementType(null, "기타"));
        };
    }
}
