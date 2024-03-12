package com.buffet.buffet;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
@Slf4j
public class BuffetApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuffetApplication.class, args);
		openSwagger();
	}
@Bean
	public OpenAPI customApi(){

return new OpenAPI().info(new Info().title("Buffet Apis").version("0.11").
		description("Apis de desarrollo buffet").termsOfService("http://swagger.io/terms/").
		license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
	private static void openSwagger() {
		try {
			URI swaggerUri = new URI("http://localhost:8080/doc/swagger-ui/index.html");

			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				Desktop.getDesktop().browse(swaggerUri);
			} else {
				log.info(""+swaggerUri);

			}
		} catch (Exception e) {
		log.error("Error "+e.getMessage());
		}
	}
}
