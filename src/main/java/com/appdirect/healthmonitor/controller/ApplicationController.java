package com.appdirect.healthmonitor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.healthmonitor.model.ApplicationDTO;
import com.appdirect.healthmonitor.model.StatusDTO;

@RestController
@RequestMapping(
        value = {"/api/v1/applications/"},
        produces = {"application/json"},
        consumes = {"application/json"}
)
public class ApplicationController {
    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<ApplicationDTO> getAll(@PathVariable("active") boolean onlyActiveApplications) {
        return null;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "{id}/"
    )
    public ApplicationDTO getById(@PathVariable("id") Long applicationId) {
        return null;
    }

    @RequestMapping(
            method = RequestMethod.POST
    )
    public void create(@RequestBody ApplicationDTO application) {

    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "{id}/"
    )
    public void delete(@PathVariable("id") Long applicationId) {

    }

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/{id}/status"}
    )
    public List<StatusDTO> getAppStatus(@PathVariable("id") Long applicationId) {
        return null;
    }

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/{id}/status/last"}
    )
    public StatusDTO getLastAppStatus(@PathVariable("id") Long applicationId) {
        return null;
    }
}
