package com.appdirect.healthmonitor.converter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.appdirect.healthmonitor.entity.Status;
import com.appdirect.healthmonitor.mapper.EntityMapper;
import com.appdirect.healthmonitor.model.StatusDTO;
import ma.glasnost.orika.BoundMapperFacade;

@Component
public class StatusBeanToEntityConverter implements Converter<StatusDTO, Status> {

	private BoundMapperFacade<StatusDTO, Status> facade;

	@Autowired
	public EntityMapper mapper;

	@Override
	public Status convert(StatusDTO source) {
		return facade.map(source);
	}

	@PostConstruct
	public void initialize() {
		facade = mapper.dedicatedMapperFor(StatusDTO.class, Status.class);
	}
}
