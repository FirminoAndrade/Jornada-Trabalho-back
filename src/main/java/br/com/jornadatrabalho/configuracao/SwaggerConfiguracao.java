package br.com.jornadatrabalho.configuracao;


import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguracao {

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("br.com.jornadatrabalho").pathsToMatch("/**").build();
	}
}
