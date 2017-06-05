package com.appdirect.healthmonitor.task;

import java.util.Collection;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;

import com.appdirect.healthmonitor.service.ApplicationService;

public abstract class AbstractTask<T> implements Runnable {

	protected ApplicationService applicationService;

	protected Supplier<Collection<T>> supplier;

	public AbstractTask(Supplier<Collection<T>> supplier) {
		this.supplier = supplier;
	}

	@Autowired
	public AbstractTask setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
		return this;
	}

	protected Collection<T> getObjects(){
		return supplier.get();
	}

}
