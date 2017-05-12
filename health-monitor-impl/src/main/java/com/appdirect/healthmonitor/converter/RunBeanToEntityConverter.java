package com.appdirect.healthmonitor.converter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.appdirect.healthmonitor.entity.Run;
import com.appdirect.healthmonitor.mapper.EntityMapper;
import com.appdirect.healthmonitor.model.RunDTO;
import ma.glasnost.orika.BoundMapperFacade;

@Component
public class RunBeanToEntityConverter implements Converter<RunDTO, Run> {

	private BoundMapperFacade<RunDTO, Run> facade;

	@Autowired
	public EntityMapper mapper;

	@Override
	public Run convert(RunDTO source) {
		return facade.map(source);
	}

	@PostConstruct
	public void initialize() {
		facade = mapper.dedicatedMapperFor(RunDTO.class, Run.class);
	}
}
