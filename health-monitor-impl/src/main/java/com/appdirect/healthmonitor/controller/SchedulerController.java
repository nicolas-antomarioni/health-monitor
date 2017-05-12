package com.appdirect.healthmonitor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.healthmonitor.model.SchedulerDTO;
import com.appdirect.healthmonitor.service.SchedulerService;

@RestController
@RequestMapping(
		value = {"/api/v1/schedulers"},
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE
)
public class SchedulerController {

	private static final int DEFAULT_PAGE_SIZE = 100;
	private static final int DEFAULT_PAGE_START = 0;

	@Autowired
	private SchedulerService schedulerService;

	@RequestMapping(
			method = RequestMethod.GET
	)
	public ResponseEntity<List<SchedulerDTO>> getAll(
			@PageableDefault(size = DEFAULT_PAGE_SIZE, page = DEFAULT_PAGE_START) Pageable pageable,
			PagedResourcesAssembler<SchedulerDTO> assembler) {
		Page<SchedulerDTO> schedulerDTOS = schedulerService.findAll(pageable);
		return new ResponseEntity<>(schedulerDTOS.getContent(), HttpStatus.OK);
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/{id}"
	)
	public SchedulerDTO getById(@PathVariable("id") Integer id) {
		return schedulerService.find(id);
	}

	@RequestMapping(
			method = RequestMethod.POST
	)
	public SchedulerDTO create(@RequestBody SchedulerDTO schedulerDTO) {
		return schedulerService.save(schedulerDTO);
	}

	@RequestMapping(
			method = RequestMethod.DELETE,
			value = "/{id}"
	)
	public void delete(@PathVariable("id") Integer id) {
		schedulerService.delete(id);
	}
}
