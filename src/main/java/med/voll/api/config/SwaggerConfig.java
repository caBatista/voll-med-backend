package med.voll.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info =
@Info(title = "VollMed - Alura Project",
		description = "This project involves developing a secure and well-structured REST API using Spring Boot, with a focus on applying best practices and ensuring robust security. It features a RESTful design with clear separation of concerns through layered architecture, centralized exception handling, and comprehensive input validation. The API is fortified with JWT-based authentication, role-based authorization, and protections against common vulnerabilities. Additionally, it includes interactive API documentation, thorough testing, and deployment strategies for various environments, exemplifying high standards in API development and security.",
		version = "v1"))
public class SwaggerConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("bearer-key"
								, new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
	}
}