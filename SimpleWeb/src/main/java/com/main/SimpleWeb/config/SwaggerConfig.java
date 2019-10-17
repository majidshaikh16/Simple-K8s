package com.main.SimpleWeb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configure swagger for api documentation.
 *
 * @author webwerks
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

	/**
	 * Allow swagger to access the resources needed for api documentation
	 *
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	/**
	 * Configure the api package to be scanned.
	 *
	 * @return {@link Docket}
	 */

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.main.SimpleWeb.controller")).paths(PathSelectors.any())
				.build();
	}

	/**
	 * Describe your apis common details
	 *
	 * @return {@link ApiInfo}
	 */
	private ApiInfo apiInfo() {
		Contact contact = new Contact("Neosoft", "https://majidshaikh16.github.io/profile/", "majidshaikh16@gmail.com");
		return new ApiInfoBuilder().title("Web Client").description("This page lists all the rest apis for K8's client")
				.version("1.0.0").license("Majid Shaikh").contact(contact).build();
	}
}
