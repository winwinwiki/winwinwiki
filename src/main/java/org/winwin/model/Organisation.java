package org.winwin.model;

import lombok.Data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Organisation")
@Data
public class Organisation extends AuditModel {
	@Id
	@GeneratedValue(generator = "org_generator")
	@SequenceGenerator(name = "org_generator", sequenceName = "org_sequence", initialValue = 1)
	private Long id;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany
	@JoinColumn(name = "org_id")
	private List<Revenue> revenue;
	
	@Column(precision=10, scale=0)
	private Long parentId;
	

	@NotBlank
	@Column(nullable = false)
	String name;
	
	@NotBlank
	@Column(nullable = false)
	String description;
	
	@Column
    Long totalAssets;
	
	@NotBlank
	@Column(nullable = false)
    String sector;
	
	@NotBlank
	@Column(nullable = false)
    String sectorLevel;
	
	@NotBlank
	@Column(nullable = false)
    String priority;
	
	@Column(nullable = false)
    boolean isTaggingReady;
	
	@NotBlank
	@Column(nullable = false)
    String tagStatus;
	
	@Column
    String website;
	
	@Column
	boolean isActive;

}