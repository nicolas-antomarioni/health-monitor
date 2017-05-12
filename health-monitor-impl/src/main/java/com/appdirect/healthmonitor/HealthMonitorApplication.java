package com.appdirect.healthmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.appdirect.healthmonitor")
public class HealthMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthMonitorApplication.class, args);
	}
}
