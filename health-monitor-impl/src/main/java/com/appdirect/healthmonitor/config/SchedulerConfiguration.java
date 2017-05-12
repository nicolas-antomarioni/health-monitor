package com.appdirect.healthmonitor.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.appdirect.healthmonitor.repository.SchedulerRepository;
import com.appdirect.healthmonitor.service.SchedulerService;

@Slf4j
@Configuration
@EnableScheduling
public class SchedulerConfiguration {

	@Autowired
	private SchedulerRepository schedulerRepository;

	@Autowired
	private SchedulerService schedulerService;

	@Scheduled(fixedDelay = 1000)
	public void runSchedulers() {
		schedulerService.getSchedulersToRun().stream()
				.parallel()
				.forEach(scheduler -> schedulerService.execute(scheduler));
	}

}
