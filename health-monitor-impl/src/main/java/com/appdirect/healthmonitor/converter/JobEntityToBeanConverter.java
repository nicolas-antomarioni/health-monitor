package com.appdirect.healthmonitor.converter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.appdirect.healthmonitor.entity.Job;
import com.appdirect.healthmonitor.mapper.EntityMapper;
import com.appdirect.healthmonitor.model.JobDTO;
import ma.glasnost.orika.BoundMapperFacade;

@Component
public class JobEntityToBeanConverter implements Converter<Job, JobDTO> {
	private BoundMapperFacade<Job, JobDTO> facade;

	@Autowired
	public EntityMapper mapper;

	@Override
	public JobDTO convert(Job source) {
		return facade.map(source);
	}

	@PostConstruct
	public void initialize() {
		facade = mapper.dedicatedMapperFor(Job.class, JobDTO.class);
	}
}
