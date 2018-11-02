package org.winwin.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Entity
@Table(name = "Region")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Region extends AuditModel {
	
	final static String INDEX_NAME = "region";  
	@Id
	@GeneratedValue(generator = INDEX_NAME + "_generator")
	@SequenceGenerator(name = INDEX_NAME+ "_generator", sequenceName = INDEX_NAME +"_sequence", initialValue = 1)
	private Long id;

	
	@NotBlank
	@Column(nullable = false)
	String city;
	
	@NotBlank
	@Column(nullable = false)
	String state;
	
	@NotBlank
	@Column(nullable = false)
	String country;
	
}
