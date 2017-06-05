package com.appdirect.healthmonitor.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "statuses")
public class Status implements Serializable {

	private static final long serialVersionUID = 5585342490102046724L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "retrieved_at", nullable = false)
	private Instant retrievedAt;

	@Column(name = "message", nullable = false)
	private String message;

	@ManyToOne
	@JoinColumn(name="application_id", referencedColumnName="id")
	private Application application;

}
