package io.pivotal.httpconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class UnpooledRestTemplateConfiguration {


	@Bean
	public RestTemplate unpooledRestTemplate() {
		return new RestTemplate();
	}

}