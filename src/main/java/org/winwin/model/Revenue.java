package org.winwin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "Revenue")
@Data
public class Revenue extends AuditModel {
	@Id
	@GeneratedValue(generator = "revenue_generator")
	@SequenceGenerator(name = "revenue_generator", sequenceName = "revenue_sequence", initialValue = 1)
	private Long id;
	
	@NotBlank
	@Column(precision=4, scale=0)
	private Long year;

	@Column(precision=20, scale=2)
	private Double amount;
	
	@Column(columnDefinition = "TEXT")
	@Size(min = 1, max = 5)
	private Double currency;

	@Column(precision=20, scale=2)
	private Long org_id;
}
