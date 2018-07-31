package org.winwin.model;

import lombok.Data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	@JoinColumn(name = "id")
	private List<Revenue> revenue;
}