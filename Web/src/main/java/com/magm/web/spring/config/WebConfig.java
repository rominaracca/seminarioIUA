package com.magm.web.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.magm.web.services.Constants;

@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	//Habilito Cors para toda la apicacion
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping(Constants.URL_BASE+"/**")  //Habilito a ese path/ y todos sus descendientes
		.allowedOrigins("*")
		.allowedMethods("PUT", "POST", "GET", "DELETE"); 
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver r = new CommonsMultipartResolver();
		r.setMaxUploadSize(500000000);
		return r;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}
