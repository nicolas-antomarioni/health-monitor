package com.appdirect.healthmonitor.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.appdirect.healthmonitor.entity.Scheduler;

public interface SchedulerRepository extends CrudRepository<Scheduler, Integer> {

	List<Scheduler> findAll();

	Page<Scheduler> findAll(Pageable pageable);

	Page<Scheduler> findByActiveTrue(Pageable pageable);

	@Query("SELECT s FROM Scheduler s WHERE (s.lastRun + s.interval) > current_date")
	List<Scheduler> findReadyToRun();
}
