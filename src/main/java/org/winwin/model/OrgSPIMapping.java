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
@Table(name = "OrgSPIMapping")
@Data
public class OrgSPIMapping extends AuditModel{
	
	final static String INDEX_NAME = "orgspim";  
	@Id
	@GeneratedValue(generator = INDEX_NAME + "_generator")
	@SequenceGenerator(name = INDEX_NAME+ "_generator", sequenceName = INDEX_NAME +"_sequence", initialValue = 1)
	private Long id;


	@Column
	Long orgId;


	@Column
	Long spiId;
	
	@Column(nullable = false)
	String tagType;


	@Column(nullable = false)
	String value;

	@Column
	Boolean isValid;
}
