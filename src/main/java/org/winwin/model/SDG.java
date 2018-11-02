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
	
  
	final static String INDEX_NAME = "sdg";  
	@Id
	@GeneratedValue(generator = INDEX_NAME + "_generator")
	@SequenceGenerator(name = INDEX_NAME+ "_generator", sequenceName = INDEX_NAME +"_sequence", initialValue = 1)
	private Long id;

	
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
