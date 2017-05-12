package com.appdirect.healthmonitor.service;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.appdirect.healthmonitor.converter.SchedulerBeanToEntityConverter;
import com.appdirect.healthmonitor.converter.SchedulerEntityToBeanConverter;
import com.appdirect.healthmonitor.entity.Scheduler;
import com.appdirect.healthmonitor.exception.RepositoryException;
import com.appdirect.healthmonitor.model.SchedulerDTO;
import com.appdirect.healthmonitor.repository.SchedulerRepository;
import com.appdirect.healthmonitor.util.DateTimeGMTProvider;

@Slf4j
@Service
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	private SchedulerRepository schedulerRepository;

	@Autowired
	private SchedulerBeanToEntityConverter schedulerBeanToEntityConverter;

	@Autowired
	private SchedulerEntityToBeanConverter schedulerEntityToBeanConverter;

	@Override
	public List<SchedulerDTO> getSchedulersToRun() {
		return schedulerRepository.findAll().stream()
				.filter( scheduler -> scheduler.getLastRun().isBefore(DateTimeGMTProvider.getCurrentInstant().minusMillis(scheduler.getInterval())))
				.map(scheduler -> schedulerEntityToBeanConverter.convert(scheduler))
				.collect(Collectors.toList());
	}

	@Override
	public void execute(SchedulerDTO schedulerDTO) {
		updateSchedulerLastRun(schedulerDTO);
		log.info("Running scheduler id = {}, description = {}", schedulerDTO.getId(), schedulerDTO.getDescription());
		schedulerDTO.getJobs().forEach(job -> {
			try {
				log.info("Running job id = {}, type = {}", job.getId(), job.getTask());
				job.getTask().getTaskClass().newInstance().run();
			} catch (Exception e) {
				log.error("Cannot execute job = {}", job, e);
			}
		});
	}

	@Override
	public Page<SchedulerDTO> findAll(Pageable pageable) {
		Page<Scheduler> entities = schedulerRepository.findAll(pageable);
		List<SchedulerDTO> content = entities.getContent().stream()
				.map(schedulerEntityToBeanConverter::convert)
				.collect(Collectors.toList());

		return new PageImpl<>(content, pageable, entities.getTotalElements());
	}

	@Override
	public SchedulerDTO find(Integer id) {
		log.info("Reading Scheduler id = {}", id);
		Scheduler scheduler = schedulerRepository.findOne(id);
		return schedulerEntityToBeanConverter.convert(scheduler);
	}

	@Override
	public SchedulerDTO save(SchedulerDTO schedulerDTO) {
		log.info("Saving Scheduler = {} ", schedulerDTO);
		if (schedulerDTO.getLastRun() == null) {
			schedulerDTO.setLastRun(DateTimeGMTProvider.getCurrentInstant());
		}
		if (schedulerDTO.getEnabled() == null) {
			schedulerDTO.setEnabled(true);
		}
		Scheduler scheduler = schedulerRepository.save(schedulerBeanToEntityConverter.convert(schedulerDTO));
		return schedulerEntityToBeanConverter.convert(scheduler);
	}

	@Override
	public SchedulerDTO update(SchedulerDTO schedulerDTO) {
		log.info("Updating Scheduler id = {}", schedulerDTO.getId());

		Scheduler oldEntity = readEntity(schedulerDTO.getId());
		Scheduler updatedEntity = updateEntity(schedulerDTO, oldEntity);

		Scheduler eventEntity = schedulerRepository.save(updatedEntity);
		return schedulerEntityToBeanConverter.convert(eventEntity);
	}

	@Override
	public void delete(Integer id) {
		log.info("Deleting Scheduler id = {}", id);
		schedulerRepository.delete(id);
	}

	private Scheduler readEntity(Integer id) {
		Scheduler scheduler = schedulerRepository.findOne(id);
		if (scheduler == null) {
			throw new RepositoryException("Scheduler entity with id = " + id + " is null");
		}
		return scheduler;
	}

	private Scheduler updateEntity(SchedulerDTO updatedBean, Scheduler entity) {
		if (updatedBean.getName() != null) {
			if (isBlank(updatedBean.getName())) {
				entity.setName(null);
			} else {
				entity.setName(updatedBean.getName());
			}
		}
		if (updatedBean.getDescription() != null) {
			if (isBlank(updatedBean.getDescription())) {
				entity.setDescription(null);
			} else {
				entity.setDescription(updatedBean.getDescription());
			}
		}
		if (updatedBean.getJobs() != null) {
			entity.setJobs(updatedBean.getJobs());
		}
		if (updatedBean.getLastRun() != null) {
			entity.setLastRun(updatedBean.getLastRun());
		}
		if (updatedBean.getInterval() != null) {
			entity.setInterval(updatedBean.getInterval());
		}
		if (updatedBean.getEnabled() != null) {
			entity.setEnabled(updatedBean.getEnabled());
		}
		return entity;
	}

	private void updateSchedulerLastRun(SchedulerDTO schedulerDTO) {
		updateSchedulerLastRun(schedulerBeanToEntityConverter.convert(schedulerDTO));
	}

	private void updateSchedulerLastRun(Scheduler scheduler) {
		scheduler.setLastRun(DateTimeGMTProvider.getCurrentInstant());
		schedulerRepository.save(scheduler);
	}
}
