package com.appdirect.healthmonitor.service;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.appdirect.healthmonitor.converter.JobBeanToEntityConverter;
import com.appdirect.healthmonitor.converter.JobEntityToBeanConverter;
import com.appdirect.healthmonitor.converter.RunBeanToEntityConverter;
import com.appdirect.healthmonitor.entity.Job;
import com.appdirect.healthmonitor.exception.RepositoryException;
import com.appdirect.healthmonitor.model.ApplicationDTO;
import com.appdirect.healthmonitor.model.JobDTO;
import com.appdirect.healthmonitor.repository.JobRepository;

@Slf4j
@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository JobRepository;

	@Autowired
	private JobBeanToEntityConverter JobBeanToEntityConverter;

	@Autowired
	private JobEntityToBeanConverter JobEntityToBeanConverter;

	@Autowired
	private RunBeanToEntityConverter runBeanToEntityConverter;

	@Autowired
	private ApplicationService applicationService;

	@Override
	public void execute(JobDTO jobDTO) {
		log.info("Started execution of job id = {}, type = {}", jobDTO.getId(), jobDTO.getTask());
		try {
			jobDTO.getTask()
					.getTaskClass()
					.getDeclaredConstructor(Supplier.class)
					.newInstance((Supplier<Collection<ApplicationDTO>>) jobDTO::getApplications)
					.setApplicationService(applicationService)
					.run();
		} catch (Exception e) {
			log.error("Cannot execute job = {}", jobDTO, e);
		}
		log.info("Finished execution of job id = {}, type = {}", jobDTO.getId(), jobDTO.getTask());
	}

	@Override
	public Page<JobDTO> findAll(Pageable pageable) {
		Page<Job> entities = JobRepository.findAll(pageable);
		List<JobDTO> content = entities.getContent().stream()
				.map(JobEntityToBeanConverter::convert)
				.collect(Collectors.toList());

		return new PageImpl<>(content, pageable, entities.getTotalElements());
	}

	@Override
	public JobDTO find(Integer id) {
		log.info("Reading Job id = {}", id);
		Job Job = JobRepository.findOne(id);
		return JobEntityToBeanConverter.convert(Job);
	}

	@Override
	public JobDTO save(JobDTO JobDTO) {
		log.info("Saving Job id = ", JobDTO.getId());
		Job job = JobRepository.save(JobBeanToEntityConverter.convert(JobDTO));
		return JobEntityToBeanConverter.convert(job);
	}

	@Override
	public JobDTO update(JobDTO JobDTO) {
		log.info("Updating Job id = {}", JobDTO.getId());

		Job oldEntity = readEntity(JobDTO.getId());
		Job updatedEntity = updateEntity(JobDTO, oldEntity);

		Job eventEntity = JobRepository.save(updatedEntity);
		return JobEntityToBeanConverter.convert(eventEntity);
	}

	@Override
	public void delete(Integer id) {
		log.info("Deleting Job id = {}", id);
		JobRepository.delete(id);
	}

	@Override
	public ApplicationDTO addApplicationToJob(Integer id, ApplicationDTO applicationDTO) {
		JobDTO jobDTO = find(id);
		ApplicationDTO savedApplication = applicationService.save(applicationDTO);
		jobDTO.add(savedApplication);
		save(jobDTO);
		return applicationService.save(applicationDTO);
	}

	private Job readEntity(Integer id) {
		Job job = JobRepository.findOne(id);
		if (job == null) {
			throw new RepositoryException("Job entity with id = " + id + " is null");
		}
		return job;
	}

	private Job updateEntity(JobDTO updatedBean, Job entity) {
		if (updatedBean.getTask() != null) {
			entity.setTask(updatedBean.getTask());
		}
		if (updatedBean.getRuns() != null) {
			entity.setRuns(
					updatedBean.getRuns()
							.stream()
							.map(runBeanToEntityConverter::convert)
							.collect(Collectors.toSet())
			);
		}
		return entity;
	}
}
