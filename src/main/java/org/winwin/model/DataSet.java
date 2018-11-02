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
@Table(name = "DataSet")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class DataSet extends AuditModel {
	
	final static String INDEX_NAME = "dataset";  
	@Id
	@GeneratedValue(generator = INDEX_NAME + "_generator")
	@SequenceGenerator(name = INDEX_NAME+ "_generator", sequenceName = INDEX_NAME +"_sequence", initialValue = 1)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	String name;
	
	@NotBlank
	@Column(nullable = false)
	String description;
	
	@NotBlank
	@Column(nullable = false)
	String type;
	
	@Column
	String url;

}
