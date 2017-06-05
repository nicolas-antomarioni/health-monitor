package com.appdirect.healthmonitor.model;

import java.time.Instant;

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
public class RunDTO {

	private Job job;

	private Instant executedAt;

	private Long executionTime;
}
