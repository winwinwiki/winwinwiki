package org.winwin.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "SPIData")
@Data
public class SPI extends AuditModel {

	private static final long serialVersionUID = 7548032244417380713L;

	@Id
	@GeneratedValue(generator = "spi_generator")
	@SequenceGenerator(name = "spi_generator", sequenceName = "spi_sequence", initialValue = 1)
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
