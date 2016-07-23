package com.jrd.itmas_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
		basePackageClasses = { ItmasServerApplication.class, Jsr310JpaConverters.class }
)
@SpringBootApplication
@EnableConfigurationProperties({ LiquibaseProperties.class })
public class ItmasServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ItmasServerApplication.class, args);
	}
}