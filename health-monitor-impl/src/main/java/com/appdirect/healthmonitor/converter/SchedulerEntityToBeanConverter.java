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
public class SchedulerEntityToBeanConverter implements Converter<Scheduler, SchedulerDTO> {
	private BoundMapperFacade<Scheduler, SchedulerDTO> facade;

	@Autowired
	public EntityMapper mapper;

	@Override
	public SchedulerDTO convert(Scheduler source) {
		return facade.map(source);
	}

	@PostConstruct
	public void initialize() {
		facade = mapper.dedicatedMapperFor(Scheduler.class, SchedulerDTO.class);
	}
}
