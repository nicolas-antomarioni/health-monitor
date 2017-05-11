package com.appdirect.healthmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.appdirect.healthmonitor.config.ApplicationConfig;

@SpringBootApplication
@Import(ApplicationConfig.class)
public class HealthMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthMonitorApplication.class, args);
	}
}
