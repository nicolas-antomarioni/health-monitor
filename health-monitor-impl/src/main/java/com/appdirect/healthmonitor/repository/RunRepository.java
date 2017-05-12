package com.appdirect.healthmonitor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.appdirect.healthmonitor.entity.Run;

public interface RunRepository extends CrudRepository<Run, Integer> {

	Page<Run> findAll(Pageable pageable);
}
