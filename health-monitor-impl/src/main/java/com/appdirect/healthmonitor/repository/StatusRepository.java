package com.appdirect.healthmonitor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.appdirect.healthmonitor.entity.Status;

public interface StatusRepository extends CrudRepository<Status, Integer> {

	Page<Status> findAll(Pageable pageable);
}
