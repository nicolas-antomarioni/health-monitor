package com.appdirect.healthmonitor.converter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.appdirect.healthmonitor.entity.Scheduler;
import com.appdirect.healthmonitor.mapper.EntityMapper;
import com.appdirect.healthmonitor.model.SchedulerDTO;
import ma.glasnost.orika.BoundMapperFacade;

@Component
public class SchedulerBeanToEntityConverter implements Converter<SchedulerDTO, Scheduler> {

	private BoundMapperFacade<SchedulerDTO, Scheduler> facade;

	@Autowired
	public EntityMapper mapper;

	@Override
	public Scheduler convert(SchedulerDTO source) {
		return facade.map(source);
	}

	@PostConstruct
	public void initialize() {
		facade = mapper.dedicatedMapperFor(SchedulerDTO.class, Scheduler.class);
	}
}
