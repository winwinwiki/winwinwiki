package org.winwin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "SDGData")
public class SDG extends AuditModel{
	
	@Id
	@GeneratedValue(generator = "sdg_generator")
	@SequenceGenerator(name = "sdg_generator", sequenceName = "sdg_sequence", initialValue = 1)
	private long id;
	
	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String shortName;
	
	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String SDGCode;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String goal;
	
	@Column(precision=5, scale=0)
	private Long goalNumber;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String completeGoalName;

}
