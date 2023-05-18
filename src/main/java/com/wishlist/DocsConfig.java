package com.wishlist;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI config.
 */
@Configuration
public class DocsConfig {

    /**
     * OpenAPI customizations.
     * @return OpenAPI bean.
     */
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .components(new Components())
            .info(new Info().title("Wishlist API"));
    }

}
