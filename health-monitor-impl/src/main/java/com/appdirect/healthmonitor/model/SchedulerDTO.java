package com.appdirect.healthmonitor.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.appdirect.healthmonitor.entity.Job;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SchedulerDTO {

	private Integer id;

	private String name;

	private String description;

	private Set<Job> jobs = new HashSet<>();

	private Instant lastRun;

	private Long interval;

	private Boolean enabled;
}
