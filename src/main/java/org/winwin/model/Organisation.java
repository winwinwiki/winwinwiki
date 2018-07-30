package org.winwin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Organisation")
public class Organisation extends AuditModel {
	
	@Id
	@GeneratedValue(generator = "org_generator")
	@SequenceGenerator(name = "org_generator", sequenceName = "org_sequence", initialValue = 1)
	private Long id;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 3, max = 100)
	private String useremail;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 3, max = 100)
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}