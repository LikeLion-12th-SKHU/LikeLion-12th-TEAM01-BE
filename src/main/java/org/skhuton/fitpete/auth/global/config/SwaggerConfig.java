package org.skhuton.fitpete.auth.global.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .version("v1.0") //버전
                .title("PeteFit API") //이름
                .description("PeteFit API"); //설명

        Server server = new Server();
        server.setUrl("https://kyulimcho.shop");

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }

}