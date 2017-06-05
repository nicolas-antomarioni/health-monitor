package com.appdirect.healthmonitor.type;

import lombok.Getter;

import com.appdirect.healthmonitor.task.AbstractTask;
import com.appdirect.healthmonitor.task.CheckHealthTask;
import com.appdirect.healthmonitor.task.CheckInfoTask;

@Getter
public enum TaskType {
	CHECK_INFO(CheckInfoTask.class, "Consume the info endpoint and store the result"),
	CHECK_HEALTH(CheckHealthTask.class, "Consume the health endpoint and store the result");

	private final Class<? extends AbstractTask> taskClass;
	private final String description;

	TaskType(Class<? extends AbstractTask> taskClass, String description) {
		this.taskClass = taskClass;
		this.description = description;
	}
}
