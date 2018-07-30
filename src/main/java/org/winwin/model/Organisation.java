package org.winwin.model;

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

	public List<Revenue> getRevenue() {
		return revenue;
	}

	public void setRevenue(List<Revenue> revenue) {
		this.revenue = revenue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}