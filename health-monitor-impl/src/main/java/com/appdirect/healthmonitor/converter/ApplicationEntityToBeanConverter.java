package com.appdirect.healthmonitor.converter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.appdirect.healthmonitor.entity.Application;
import com.appdirect.healthmonitor.mapper.EntityMapper;
import com.appdirect.healthmonitor.model.ApplicationDTO;
import ma.glasnost.orika.BoundMapperFacade;

@Component
public class ApplicationEntityToBeanConverter implements Converter<Application, ApplicationDTO> {
	private BoundMapperFacade<Application, ApplicationDTO> facade;

	@Autowired
	public EntityMapper mapper;

	@Override
	public ApplicationDTO convert(Application source) {
		return facade.map(source);
	}

	@PostConstruct
	public void initialize() {
		facade = mapper.dedicatedMapperFor(Application.class, ApplicationDTO.class);
	}
}
