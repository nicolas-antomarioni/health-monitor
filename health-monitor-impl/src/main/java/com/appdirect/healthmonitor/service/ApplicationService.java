package com.appdirect.healthmonitor.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.appdirect.healthmonitor.model.ApplicationDTO;

public interface ApplicationService {

	void checkHealth(ApplicationDTO applicationDTO);

	void checkInfo(ApplicationDTO applicationDTO);

	ApplicationDTO register(ApplicationDTO applicationDTO);

	ApplicationDTO find(Integer id);

	Page<ApplicationDTO> findAll(Pageable pageable);

	Page<ApplicationDTO> findAllActive(Pageable pageable);

	ApplicationDTO save(ApplicationDTO applicationDTO);

	ApplicationDTO update(ApplicationDTO applicationDTO);

	void delete(Integer id);
}
