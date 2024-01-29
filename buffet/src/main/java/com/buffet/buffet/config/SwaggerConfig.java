package com.buffet.buffet.config;

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

//@Configuration
//@EnableSwagger2
public class SwaggerConfig {
    //@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select().
                apis(RequestHandlerSelectors.basePackage("buffet")).paths(PathSelectors.any()).build();
    }
    private ApiInfo getApiInfo(){
        Contact contact = new Contact("bff","http://localhost","christianalonso1616@gmail.com");
    return new ApiInfoBuilder().title("API BUFFET").description("Documentacion Buffet Api").
            version("1.0").license("Apache 2.0").licenseUrl("http://localhost").
            contact(contact).build();
    }
}
