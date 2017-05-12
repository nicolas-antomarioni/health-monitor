package com.appdirect.healthmonitor.model;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.appdirect.healthmonitor.entity.Run;
import com.appdirect.healthmonitor.type.TaskType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class JobDTO {

	private Integer id;

	private TaskType task;

	private Set<Run> runs = new HashSet<>();
}
