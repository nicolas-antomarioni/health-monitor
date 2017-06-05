package com.appdirect.healthmonitor.model;


import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ApplicationDTO {

	private Integer id;

	private String name;

	private String baseUrl;

	private Set<StatusDTO> statuses = new HashSet<>();

	private Boolean infoEnabled;

	private Boolean healthEnabled;

	private Boolean active;
}
