package com.appdirect.healthmonitor.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.appdirect.healthmonitor.model.ApplicationDTO;
import com.appdirect.healthmonitor.model.JobDTO;

public interface JobService {

	void execute(JobDTO job);

	JobDTO find(Integer id);

	Page<JobDTO> findAll(Pageable pageable);

	JobDTO save(JobDTO jobDTO);

	JobDTO update(JobDTO jobDTO);

	void delete(Integer id);

	ApplicationDTO addApplicationToJob(Integer id, ApplicationDTO applicationDTO);
}
