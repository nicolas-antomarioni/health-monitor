package com.appdirect.healthmonitor.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class StatusDTO {

	private Integer id;

	private Instant retrievedAt;

	private String message;

	private ApplicationDTO application;
}
