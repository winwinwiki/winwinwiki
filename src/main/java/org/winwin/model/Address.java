package org.winwin.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Address")
@Data
public class Address extends AuditModel {
	private static final long serialVersionUID = 7217969939976608075L;

	final static String INDEX_NAME = "address";  
	@Id
	@GeneratedValue(generator = INDEX_NAME + "_generator")
	@SequenceGenerator(name = INDEX_NAME+ "_generator", sequenceName = INDEX_NAME +"_sequence", initialValue = 1)
	private Long id;


	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 1, max = 100)
	private String street;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 1, max = 100)
	private String city;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 3, max = 100)
	private String county;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 1, max = 100)
	private String state;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 1, max = 100)
	private String zip;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 1, max = 100)
	private String country;
}
