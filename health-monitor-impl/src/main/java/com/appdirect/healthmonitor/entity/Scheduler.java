package com.appdirect.healthmonitor.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "schedulers")
public class Scheduler implements Serializable {

	private static final long serialVersionUID = -701651972030697683L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@OneToMany(targetEntity = Job.class, fetch = FetchType.EAGER)
	private Set<Job> jobs = new HashSet<>();

	@Column(name = "last_run", nullable = false)
	private Instant lastRun;

	@Column(name = "interval", nullable = false)
	private Long interval;

	@Column(name = "active")
	private Boolean active;
}
