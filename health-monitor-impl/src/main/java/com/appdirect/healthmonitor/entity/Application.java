package com.appdirect.healthmonitor.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name = "applications")
public class Application implements Serializable {

	private static final long serialVersionUID = 5585342490102046724L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "base_url", nullable = false)
	private String baseUrl;

	@OneToMany(targetEntity = Status.class, fetch = FetchType.EAGER, mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Status> statuses = new HashSet<>();

	@Column(name = "info_enabled")
	private Boolean infoEnabled;

	@Column(name = "health_enabled")
	private Boolean healthEnabled;

	@Column(name = "active")
	private Boolean active;
}
