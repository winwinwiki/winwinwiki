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
@Table(name = "OrgSDGMapping")
@Data
public class OrgSDGMapping extends AuditModel{
	
	final static String INDEX_NAME = "orgsdgm";  
	@Id
	@GeneratedValue(generator = INDEX_NAME + "_generator")
	@SequenceGenerator(name = INDEX_NAME+ "_generator", sequenceName = INDEX_NAME +"_sequence", initialValue = 1)
	private Long id;
	
	@Column
	Long orgId;

	@Column
	Long sdgId;
	
	@Column
	String tagType;


	@Column
	String value;

	@Column
	Boolean isValid;
}
