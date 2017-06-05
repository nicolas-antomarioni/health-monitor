package com.appdirect.healthmonitor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.appdirect.healthmonitor.entity.Application;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {

	Page<Application> findAll(Pageable pageable);

	Page<Application> findByActiveTrue(Pageable pageable);
}
