package com.ggsoftware.opcserver.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {
	
	private ApiKey apiKey() { 
	    return new ApiKey("JWT", "Authorization", "header"); 
	}
	
	private SecurityContext securityContext() { 
	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
	} 

	private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	}
	
	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	      .apiInfo(apiInfo())
	      .securityContexts(Arrays.asList(securityContext()))
	      .securitySchemes(Arrays.asList(apiKey()))
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.ggsoftware.opcserver")).paths(PathSelectors.regex("/.*"))
	      
	      .paths(PathSelectors.any())
	      .build();
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "OPCSERVER-API",
	      "Documentação/Gerenciamento dos Endpoints - OPCServer-API",
	      "1.0",
	      "Produto Valgroup ®",
	      new Contact("GGSoftware Soluções", "https://ggsoftwaresolucoes.netlify.app", "ggsoftsolucoesx10@gmail.com"),
	      "null",
	      "null",
	      Collections.emptyList());
	}
	

//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.ggsoftware.opcserver")).paths(PathSelectors.regex("/.*"))
//				.build().directModelSubstitute(Pageable.class, PageableConfigSwagger.class)
//				.tags(new Tag("OpcItemTags", "Gerencia a consulta aos dados das ItemTags"),
//						new Tag("ItemTags", "Gerencia a consulta das ItemTags"), 
//						new Tag("Usuários", "Gerencia a consulta dos Usuários"))
//				.apiInfo(apiInfoMetaData());
//	}
//	
////	.contact(new Contact("GGSOFTWARE", "https://ggsoftwaresolucoes.netlify.app",
////			"ggsoftsolucoesx10@gmail.com"))
//
//	private ApiInfo apiInfoMetaData() {
//
//		return new ApiInfoBuilder().title("OPCSERVER-API").description("Documentação dos Endpoint OPCServer-API")
//				.version("1.0.0")
//				.build();
//	}
	

}
