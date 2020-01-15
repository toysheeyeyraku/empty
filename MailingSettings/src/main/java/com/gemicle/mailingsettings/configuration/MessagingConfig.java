package com.gemicle.mailingsettings.configuration;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class MessagingConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private Environment env;
	
	private static ApiInfo apiInfo() {
		return new ApiInfo("Logging", "", "0.1", "", new Contact("admin", "gemicle.com", "admin@gemicle.com"), "", "", null);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any()).build()
				;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(stringHttpMessageConverter());
		converters.add(mappingJackson2HttpMessageConverter());
		converters.add(byteArrayHttpMessageConverter());
		converters.add(resourceHttpMessageConverter());
		converters.add(formHttpMessageConverter());
		super.configureMessageConverters(converters);
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper());
		return converter;
	}
	
	@Bean(name= "defaultTimeZone")
	public String defaultTimeZone(){
		return env.getProperty("webserver.timezone");
	}

	@Bean(name = "objectMapper")
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		/*mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		mapper.registerModule(new JodaModule());
		mapper.setTimeZone(TimeZone.getTimeZone(defaultTimeZone()));

		SimpleModule dateModule = new SimpleModule("DateModule", new Version(1, 0, 0, null, null, null));
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mapper.registerModule(dateModule);

		SimpleModule objectIdModule = new SimpleModule("CustomObjectIdMapper", new Version(1, 0, 0, null, null, null));
		objectIdModule.addSerializer(ObjectId.class, new ObjectIdSerializer());
		objectIdModule.addDeserializer(ObjectId.class, new ObjectIdDeserializer());
		mapper.registerModule(objectIdModule);*/

		return mapper;
	}

	@Bean
	public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
		return new ByteArrayHttpMessageConverter();
	}

	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		return new StringHttpMessageConverter(StandardCharsets.UTF_8);
	}

	@Bean
	public ResourceHttpMessageConverter resourceHttpMessageConverter() {
		return new ResourceHttpMessageConverter();
	}

	@Bean
	public FormHttpMessageConverter formHttpMessageConverter() {
		return new FormHttpMessageConverter();
	}

}