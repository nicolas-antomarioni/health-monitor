package com.appdirect.healthmonitor.task;

import java.util.List;
import java.util.function.Supplier;

import com.appdirect.healthmonitor.entity.Application;

public class CheckInfoTask extends AbstractTask<Application> {

	public CheckInfoTask(Supplier<List<Application>> supplier) {
		super(supplier);
	}

	@Override
	public void run() {
		getObjects().forEach( application -> {
			//ApplicationService.checkInfo(application);
		});
	}
}