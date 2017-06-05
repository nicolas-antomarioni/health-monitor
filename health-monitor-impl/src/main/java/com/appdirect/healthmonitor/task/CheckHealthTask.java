package com.appdirect.healthmonitor.task;

import java.util.Collection;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;

import com.appdirect.healthmonitor.model.ApplicationDTO;

@Slf4j
public class CheckHealthTask extends AbstractTask<ApplicationDTO> {

	public CheckHealthTask(Supplier<Collection<ApplicationDTO>> supplier) {
		super(supplier);
	}

	@Override
	public void run() {
		getObjects().forEach( application -> {
			log.info("Starting check Health for application = {}", application);
			applicationService.checkHealth(application);
			log.info("Finishing check Health for application = {}", application);
		});
	}
}
