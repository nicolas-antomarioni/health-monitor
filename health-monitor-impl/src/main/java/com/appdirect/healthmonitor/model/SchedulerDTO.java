package com.appdirect.healthmonitor.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Delegate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SchedulerDTO {

	private Integer id;

	private String name;

	private String description;

	@Delegate
	private Set<JobDTO> jobs = new HashSet<>();

	private Instant lastRun;

	private Long interval;

	private Boolean active;
}
