package com.appdirect.healthmonitor.service;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.appdirect.healthmonitor.converter.ApplicationBeanToEntityConverter;
import com.appdirect.healthmonitor.converter.ApplicationEntityToBeanConverter;
import com.appdirect.healthmonitor.converter.StatusBeanToEntityConverter;
import com.appdirect.healthmonitor.entity.Application;
import com.appdirect.healthmonitor.exception.RepositoryException;
import com.appdirect.healthmonitor.model.ApplicationDTO;
import com.appdirect.healthmonitor.model.JobDTO;
import com.appdirect.healthmonitor.model.SchedulerDTO;
import com.appdirect.healthmonitor.repository.ApplicationRepository;
import com.appdirect.healthmonitor.type.TaskType;
import com.appdirect.healthmonitor.util.DateTimeGMTProvider;

@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private ApplicationBeanToEntityConverter applicationBeanToEntityConverter;

	@Autowired
	private ApplicationEntityToBeanConverter applicationEntityToBeanConverter;

	@Autowired
	private StatusBeanToEntityConverter statusBeanToEntityConverter;

	@Autowired
	private JobService jobService;

	@Autowired
	private SchedulerService schedulerService;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void checkHealth(ApplicationDTO applicationDTO) {
		log.info("Checking Health of Application = {}", applicationDTO);
		Object response = restTemplate.getForEntity(getHealthUrl(applicationDTO), Object.class);
		log.info("Health = {}", response);
	}

	@Override
	public void checkInfo(ApplicationDTO applicationDTO) {
		log.info("Checking Info of Application = {}", applicationDTO);
		//TODO consume the real endpoint
	}

	@Override
	public ApplicationDTO register(ApplicationDTO applicationDTO) {
		applicationDTO.setActive(true);
		ApplicationDTO savedApplication = save(applicationDTO);
		JobDTO jobDTO = JobDTO.builder()
				.task(TaskType.CHECK_HEALTH)
				.applications(Collections.singleton(savedApplication))
				.build();
		JobDTO savedJob = jobService.save(jobDTO);
		SchedulerDTO schedulerDTO = SchedulerDTO.builder()
				.name("Auto-" + savedApplication.getId())
				.description("Automatic Scheduler generated for the Application id " + savedApplication.getId())
				.jobs(Collections.singleton(savedJob))
				.lastRun(DateTimeGMTProvider.getCurrentInstant())
				.interval(15000L)
				.active(true)
				.build();
		schedulerService.save(schedulerDTO);
		return savedApplication;
	}

	@Override
	public Page<ApplicationDTO> findAll(Pageable pageable) {
		Page<Application> entities = applicationRepository.findAll(pageable);
		List<ApplicationDTO> content = entities.getContent().stream()
				.map(applicationEntityToBeanConverter::convert)
				.collect(Collectors.toList());

		return new PageImpl<>(content, pageable, entities.getTotalElements());
	}

	@Override
	public Page<ApplicationDTO> findAllActive(Pageable pageable) {
		Page<Application> entities = applicationRepository.findByActiveTrue(pageable);
		List<ApplicationDTO> content = entities.getContent().stream()
				.map(applicationEntityToBeanConverter::convert)
				.collect(Collectors.toList());

		return new PageImpl<>(content, pageable, entities.getTotalElements());
	}

	@Override
	public ApplicationDTO find(Integer id) {
		log.info("Reading Job id = {}", id);
		Application Job = applicationRepository.findOne(id);
		return applicationEntityToBeanConverter.convert(Job);
	}

	@Override
	public ApplicationDTO save(ApplicationDTO applicationDTO) {
		log.info("Saving Job id = ", applicationDTO.getId());
		Application Job = applicationRepository.save(applicationBeanToEntityConverter.convert(applicationDTO));
		return applicationEntityToBeanConverter.convert(Job);
	}

	@Override
	public ApplicationDTO update(ApplicationDTO applicationDTO) {
		log.info("Updating Job id = {}", applicationDTO.getId());

		Application oldEntity = readEntity(applicationDTO.getId());
		Application updatedEntity = updateEntity(applicationDTO, oldEntity);

		Application eventEntity = applicationRepository.save(updatedEntity);
		return applicationEntityToBeanConverter.convert(eventEntity);
	}

	@Override
	public void delete(Integer id) {
		log.info("Deleting Job id = {}", id);
		applicationRepository.delete(id);
	}

	private Application readEntity(Integer id) {
		Application application = applicationRepository.findOne(id);
		if (application == null) {
			throw new RepositoryException("Job entity with id = " + id + " is null");
		}
		return application;
	}

	private Application updateEntity(ApplicationDTO updatedBean, Application entity) {
		if (updatedBean.getName() != null) {
			if (isBlank(updatedBean.getName())) {
				entity.setName(null);
			} else {
				entity.setName(updatedBean.getName());
			}
		}
		if (updatedBean.getBaseUrl() != null) {
			if (isBlank(updatedBean.getBaseUrl())) {
				entity.setBaseUrl(null);
			} else {
				entity.setBaseUrl(updatedBean.getName());
			}
		}
		if (updatedBean.getStatuses() != null) {
			entity.setStatuses(
					updatedBean.getStatuses()
							.stream()
							.map(statusBeanToEntityConverter::convert)
							.collect(Collectors.toSet())
			);
		}
		if (updatedBean.getActive() != null) {
			entity.setActive(updatedBean.getActive());
		}
		return entity;
	}

	private String getHealthUrl(ApplicationDTO applicationDTO) {
		return applicationDTO.getBaseUrl() + "/health";
	}
}
