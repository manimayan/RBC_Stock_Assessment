package com.rbc.stock.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.OAS_30)
                .select().apis(RequestHandlerSelectors.basePackage("com.rbc.stock"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("RBC Stock Service")
                .description("Contain API endpoints and sample Request/Response object of Financial Stock Service Application.")
                .version("1.0.0")
                .license("Source Code")
                .licenseUrl("https://github.com/manimayan/RBC_Stock_Assessment")
                .contact(new Contact("Manimaran Palani", "https://github.com/manimayan/RBC_Stock_Assessment", "manimayan11@gmail.com"))
                .build();
    }
}