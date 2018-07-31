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
@Table(name = "AccountHolder")
@Data
public class AccountHolder extends AuditModel {
	
	@Id
	@GeneratedValue(generator = "user_generator")
	@SequenceGenerator(name = "user_generator", sequenceName = "user_sequence", initialValue = 1000)
	private Long id;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 3, max = 100)
	private String useremail;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 3, max = 100)
	private String password;
}