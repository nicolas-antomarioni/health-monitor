package com.appdirect.healthmonitor.mapper;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class EntityMapper extends ConfigurableMapper {
	@Override
	protected void configure(MapperFactory factory) {
		factory.getConverterFactory().registerConverter(new PassThroughConverter(java.time.Instant.class));
	}
}
