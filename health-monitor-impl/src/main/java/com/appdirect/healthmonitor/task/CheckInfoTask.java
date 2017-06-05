package com.appdirect.healthmonitor.task;

import java.util.Collection;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import com.appdirect.healthmonitor.model.ApplicationDTO;
import com.appdirect.healthmonitor.service.ApplicationService;

@Slf4j
public class CheckInfoTask extends AbstractTask<ApplicationDTO> {

	@Autowired
	ApplicationService applicationService;

	public CheckInfoTask(Supplier<Collection<ApplicationDTO>> supplier) {
		super(supplier);
	}

	@Override
	public void run() {
		getObjects().forEach( application -> {
			log.info("Starting check Info for application = {}", application);
			applicationService.checkInfo(application);
			log.info("Finishing check Info for application = {}", application);
		});
	}
}
