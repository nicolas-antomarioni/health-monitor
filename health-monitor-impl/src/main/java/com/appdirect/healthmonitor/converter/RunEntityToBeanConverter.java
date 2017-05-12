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
public class RunEntityToBeanConverter implements Converter<Run, RunDTO> {
	private BoundMapperFacade<Run, RunDTO> facade;

	@Autowired
	public EntityMapper mapper;

	@Override
	public RunDTO convert(Run source) {
		return facade.map(source);
	}

	@PostConstruct
	public void initialize() {
		facade = mapper.dedicatedMapperFor(Run.class, RunDTO.class);
	}
}
