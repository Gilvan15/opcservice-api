package com.ggsoftware.opcserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ggsoftware.opcserver"))
				.paths(PathSelectors.regex("/.*"))
				.build()
				.apiInfo(apiInfoMetaData());
	}
	
	private ApiInfo apiInfoMetaData() {

        return new ApiInfoBuilder().title("OPCSERVER-API")
                .description("Documentação dos Endpoint OPCServer-API")
                .contact(new Contact("GGSOFTWARE", "https://ggsoftwaresolucoes.netlify.app", "ggsoftsolucoesx10@gmail.com"))
                .version("1.0.0")
                .build();
    }

}
