package com.pods.fclabs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String BASE_PACKAGE = "com.pods.fclabs.controllers";
    private static final String TITLE = "PODS API";
    private static final String DESCRIPTION = "O PODS _FCLABS foi concebido com o objetivo de ser o provedor modelo Padrão de projeto, garantindo o setup sistemas novos.";
    private static final String VERSION = "1.0.1";
    private static final String LICENSE = "Copyright FCAMARA - 2022 - Allan Rodrigo Qualtieri";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build().apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .version(VERSION)
                .license(LICENSE)
                .build();
    }
}
