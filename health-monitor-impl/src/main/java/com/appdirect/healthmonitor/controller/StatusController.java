package com.appdirect.healthmonitor.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.healthmonitor.model.StatusDTO;

@RestController
@RequestMapping(
        value = {"/api/v1/status"},
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public class StatusController {
    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<StatusDTO> getAll() {
        return null;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}"
    )
    public StatusDTO getById(@PathVariable("id") Integer statusId) {
        return null;
    }

    @RequestMapping(
            method = RequestMethod.POST
    )
    public void create(@RequestBody StatusDTO status) {

    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/{id}"
    )
    public void delete(@PathVariable("id") Integer statusId) {

    }
}
