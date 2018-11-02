package org.winwin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "SPIData")
@Data
public class SPI extends AuditModel {

	final static String INDEX_NAME = "spi";  
	@Id
	@GeneratedValue(generator = INDEX_NAME + "_generator")
	@SequenceGenerator(name = INDEX_NAME+ "_generator", sequenceName = INDEX_NAME +"_sequence", initialValue = 1)
	private Long id;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String indicator;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String component;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String dimension;
}
