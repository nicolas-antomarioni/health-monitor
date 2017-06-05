package com.appdirect.healthmonitor.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.healthmonitor.model.ApplicationDTO;
import com.appdirect.healthmonitor.model.StatusDTO;
import com.appdirect.healthmonitor.service.ApplicationService;

@RestController
@RequestMapping(
		value = {"/api/v1/applications"},
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE
)
public class ApplicationController {

	private static final int DEFAULT_PAGE_SIZE = 100;
	private static final int DEFAULT_PAGE_START = 0;

	@Autowired
	private ApplicationService applicationService;

	@RequestMapping(
			method = RequestMethod.GET
	)
	public Page<ApplicationDTO> getAll(
			@PathVariable("active") boolean onlyActiveApplications,
			@PageableDefault(size = DEFAULT_PAGE_SIZE, page = DEFAULT_PAGE_START) Pageable pageable,
			PagedResourcesAssembler<ApplicationDTO> assembler) {
		return onlyActiveApplications ? applicationService.findAllActive(pageable) : applicationService.findAll(pageable);
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/{id}"
	)
	public ApplicationDTO getById(@PathVariable("id") Integer id) {
		return applicationService.find(id);
	}

	@RequestMapping(
			method = RequestMethod.POST,
			value = {"/register"}
	)
	public void register(@RequestBody ApplicationDTO applicationDTO) {
		applicationService.register(applicationDTO);
	}

	@RequestMapping(
			method = RequestMethod.POST
	)
	public void create(@RequestBody ApplicationDTO applicationDTO) {
		applicationService.save(applicationDTO);
	}

	@RequestMapping(
			method = RequestMethod.DELETE,
			value = "/{id}"
	)
	public void delete(@PathVariable("id") Integer id) {
		applicationService.delete(id);
	}

	@RequestMapping(
			method = {RequestMethod.GET},
			value = {"/{id}/status"}
	)
	public List<StatusDTO> getAppStatus(@PathVariable("id") Integer applicationId) {
		return new ArrayList<>();
	}

	@RequestMapping(
			method = {RequestMethod.GET},
			value = {"/{id}/status/last"}
	)
	public StatusDTO getLastAppStatus(@PathVariable("id") Integer applicationId) {
		return null;
	}
}
