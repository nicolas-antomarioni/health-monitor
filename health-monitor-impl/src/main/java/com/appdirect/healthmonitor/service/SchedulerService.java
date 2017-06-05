package com.appdirect.healthmonitor.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.appdirect.healthmonitor.model.JobDTO;
import com.appdirect.healthmonitor.model.SchedulerDTO;

public interface SchedulerService {

	List<SchedulerDTO> getSchedulersToRun();

	void execute(SchedulerDTO schedulerDTO);

	SchedulerDTO find(Integer id);

	Page<SchedulerDTO> findAll(Pageable pageable);

	SchedulerDTO save(SchedulerDTO schedulerDTO);

	SchedulerDTO update(SchedulerDTO schedulerDTO);

	void delete(Integer id);

	JobDTO addJobToScheduler(Integer id, JobDTO jobDTO);
}
