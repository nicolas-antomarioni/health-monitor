package com.appdirect.healthmonitor.task;

import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractTask<T> implements Runnable {

	protected Supplier<List<T>> supplier;

	public AbstractTask(Supplier<List<T>> supplier) {
		this.supplier = supplier;
	}

	protected List<T> getObjects(){
		return supplier.get();
	}

}
