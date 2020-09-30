package com.app.runadoc.config.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Autowired
	private SwaggerConfigProperties swaggerConfigProperties;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_12).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.app.runadoc.controller")).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {

		return new ApiInfoBuilder().title(swaggerConfigProperties.getTitle())
				.description(swaggerConfigProperties.getDescription()).version("1.0").build();
	}
}
