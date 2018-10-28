package org.winwin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "Resource")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Resource extends AuditModel {
	
	final static String INDEX_NAME = "resource";  
	@Id
	@GeneratedValue(generator = INDEX_NAME + "_generator")
	@SequenceGenerator(name = INDEX_NAME+ "_generator", sequenceName = INDEX_NAME +"_sequence", initialValue = 1)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	String category;
	
	
	@Column
	Long count;
	
	@NotBlank
	@Column(nullable = false)
	String description;

}
