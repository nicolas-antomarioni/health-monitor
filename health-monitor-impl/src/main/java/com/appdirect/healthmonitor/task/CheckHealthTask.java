package com.appdirect.healthmonitor.task;

import java.util.List;
import java.util.function.Supplier;

import com.appdirect.healthmonitor.entity.Application;

public class CheckHealthTask extends AbstractTask<Application> {

	public CheckHealthTask(Supplier<List<Application>> supplier) {
		super(supplier);
	}

	@Override
	public void run() {
		getObjects().forEach( application -> {
			//ApplicationService.checkHealth(application);
		});
	}
}
