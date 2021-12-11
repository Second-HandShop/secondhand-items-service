package com.secondhand.secondhanditemsservice;

import io.swagger.configuration.LocalDateConverter;
import io.swagger.configuration.LocalDateTimeConverter;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {
		"io.swagger", "io.swagger.api" ,
		"io.swagger.configuration",
		"com.secondhand.secondhanditemsservice.controller",
		"com.secondhand.secondhanditemsservice.impl"
})
public class SecondhandItemsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondhandItemsServiceApplication.class, args);

		System.out.println("Application Started");
	}

	@Configuration
	static class MyConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addFormatters(FormatterRegistry registry) {
			registry.addConverter(new LocalDateConverter("yyyy-MM-dd"));
			registry.addConverter(new LocalDateTimeConverter("yyyy-MM-dd'T'HH:mm:ss.SSS"));
		}
	}

	class ExitException extends RuntimeException implements ExitCodeGenerator {
		private static final long serialVersionUID = 1L;

		@Override
		public int getExitCode() {
			return 10;
		}

	}

	@Configuration
	public class DataSourceConfig {
		@Bean
		public DataSource getDataSource()
		{
			DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
			dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
			dataSourceBuilder.url("jdbc:mysql://localhost:3306/second_hand");
			dataSourceBuilder.username("root");
			dataSourceBuilder.password("Rabu#MYSQL2021");
			return dataSourceBuilder.build();
		}
	}

}
