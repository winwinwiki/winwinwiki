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
@Table(name = "Address")
public class Address extends AuditModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7217969939976608075L;

	@Id
	@GeneratedValue(generator = "address_generator")
	@SequenceGenerator(name = "address_generator", sequenceName = "address_sequence", initialValue = 1)
	private Long address_id;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 3, max = 100)
	private String street;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 3, max = 100)
	private String city;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 3, max = 100)
	private String county;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 3, max = 100)
	private String state;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 3, max = 100)
	private String zip;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	@Size(min = 3, max = 100)
	private String country;

	public Long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
