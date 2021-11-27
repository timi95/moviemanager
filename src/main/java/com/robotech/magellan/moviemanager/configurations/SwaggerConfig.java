package com.robotech.magellan.moviemanager.configurations;


import org.springframework.beans.factory.annotation.Value;
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

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${enable.swagger.plugin:true}")
    private boolean enableSwaggerPlugin;

    ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Swagger Movie Manager")
                .description("An API for all your movie needs")
                .license("MIT")
                .licenseUrl("https://opensource.org/licenses/MIT")
                .version("1.0.0")
                .contact(new Contact("Timi","https://github.com/timi95", "timi.ogunkeye@gmail.com"))
                .build();
    }
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.robotech.magellan.moviemanager"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
}