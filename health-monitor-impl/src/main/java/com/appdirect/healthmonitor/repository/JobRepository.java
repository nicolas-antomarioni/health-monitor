package com.appdirect.healthmonitor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.appdirect.healthmonitor.entity.Job;

public interface JobRepository extends CrudRepository<Job, Integer> {

	Page<Job> findAll(Pageable pageable);
}
