package com.appdirect.healthmonitor.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.appdirect.healthmonitor.type.TaskType;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "jobs")
public class Job implements Serializable {

	private static final long serialVersionUID = -5337132001150349019L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "task", nullable = false)
	@Enumerated(EnumType.STRING)
	private TaskType task;

	@OneToMany(targetEntity = Application.class, fetch = FetchType.EAGER)
	private Set<Application> applications = new HashSet<>();

	@OneToMany(targetEntity = Run.class, fetch = FetchType.EAGER, mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Run> runs = new HashSet<>();
}
