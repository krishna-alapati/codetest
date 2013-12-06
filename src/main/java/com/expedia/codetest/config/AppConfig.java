package com.expedia.codetest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

import com.expedia.codetest.integration.client.WundergroundRestClient;

/**
 * Configuration class
 * @author krishna.alapati
 *
 */
@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig {
	@Value("${rest.url}") String restUrl;
	
	@Bean
	public WundergroundRestClient wundergroundRestClient(){
		return new WundergroundRestClient(restUrl);
	}
	
	@Bean 
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	@Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}
}
