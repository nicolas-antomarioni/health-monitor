package com.appdirect.healthmonitor.converter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.appdirect.healthmonitor.entity.Status;
import com.appdirect.healthmonitor.mapper.EntityMapper;
import com.appdirect.healthmonitor.model.ApplicationDTO;
import ma.glasnost.orika.BoundMapperFacade;

@Component
public class StatusEntityToBeanConverter implements Converter<Status, ApplicationDTO> {
	private BoundMapperFacade<Status, ApplicationDTO> facade;

	@Autowired
	public EntityMapper mapper;

	@Override
	public ApplicationDTO convert(Status source) {
		return facade.map(source);
	}

	@PostConstruct
	public void initialize() {
		facade = mapper.dedicatedMapperFor(Status.class, ApplicationDTO.class);
	}
}
