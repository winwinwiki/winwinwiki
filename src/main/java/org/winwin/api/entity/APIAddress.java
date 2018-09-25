package org.winwin.api.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class APIAddress {
	private String street;
	private String city;
	private String county;
	private String state;
	private String zip;
	private String country;

	public static void main(String[] args) throws JsonProcessingException {
		APIAddress address = new APIAddress();
		address.setCity("Seattle");
		address.setStreet("DexterAve N");
		address.setCountry("USA");
		address.setCounty("King");
		address.setState("WA");
		address.setZip("98198");
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(address));
	}
}
