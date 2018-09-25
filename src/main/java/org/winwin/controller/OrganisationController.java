package org.winwin.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.winwin.api.entity.APIAddress;
import org.winwin.api.entity.CreateOrganisation;
import org.winwin.model.Address;
import org.winwin.model.Organisation;
import org.winwin.repository.AddressRepository;
import org.winwin.repository.OrganisationRepository;
import org.winwin.repository.RevenueRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrganisationController {

	@Autowired
	private OrganisationRepository orgRepo;

	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private RevenueRepository revenueRepo;
	
	@GetMapping("/organization/{id}")
	public Organisation getOrganisation(@Valid @PathVariable("id") long id) {
		return orgRepo.getOne(id);
	}
	
	@PostMapping("/organization/{id}")
	public Organisation createOrganisation(@Valid @PathVariable("id") long id,
			@Valid @RequestBody CreateOrganisation request) {
		
		if( id != 0 ) {
			Organisation org = orgRepo.getOne(id);
			if( org == null || org.isActive() == false) {
				throw new RuntimeException("Active Org doesnot exist");
			}
		}
		Organisation org = new Organisation();
		org.setUpdatedAt(new Date());
		org.setCreatedAt(new Date());
		org.setActive(true);
		org.setParentId(id);
		org.setName(request.getName());
		org.setDescription(request.getDescription());
		org.setTotalAssets(request.getTotalAssets());
		org.setSector(request.getSector());
		org.setSectorLevel(request.getSectorLevel());
		org.setPriority(request.getPriority());
		org.setTaggingReady(request.isTaggingReady());
		org.setTagStatus(request.getTagStatus());
		org.setWebsite(request.getWebsite());
		return orgRepo.save(org);
	}
	
	@DeleteMapping("/organization/{id}")
	public Organisation deleteOrganisation(@Valid @PathVariable("id") long id,
			@Valid @RequestBody CreateOrganisation request) {
		Organisation org = orgRepo.getOne(id);
		if( org != null && org.isActive() ) {
			org.setUpdatedAt(new Date());
			org.setActive(false);
			return orgRepo.save(org);
		}
		throw new RuntimeException("Active Org doesnot exist");
	}
	
	@PutMapping("/organization/{id}")
	public Organisation getOrganisation(@Valid @PathVariable("id") long id,
			@Valid @RequestBody CreateOrganisation request) {
		Organisation org = orgRepo.getOne(id);
		if( org != null && org.isActive() ) {
			org.setUpdatedAt(new Date());
			org.setName(request.getName());
			org.setName(request.getName());
			org.setDescription(request.getDescription());
			org.setTotalAssets(request.getTotalAssets());
			org.setSector(request.getSector());
			org.setSectorLevel(request.getSectorLevel());
			org.setPriority(request.getPriority());
			org.setTaggingReady(request.isTaggingReady());
			org.setTagStatus(request.getTagStatus());
			org.setWebsite(request.getWebsite());
			return orgRepo.save(org);
		}
		throw new RuntimeException("Active Org doesnot exist");
	}
	
	@PutMapping("/organization/{id}/address")
	public Organisation getOrganisation(@Valid @PathVariable("id") long id,
			@Valid @RequestBody APIAddress request) {
		Organisation org = orgRepo.getOne(id);
		if( org != null && org.isActive() ) {
			org.setUpdatedAt(new Date());
			Address address = org.getAddress();
			if( address == null ) {
				address = new Address();
				address.setCreatedAt(new Date());
			}
			address.setCity(request.getCity());
			address.setCountry(request.getCountry());
			address.setCounty(request.getCounty());
			address.setState(request.getState());
			address.setStreet(request.getStreet());
			address.setZip(request.getZip());
			address.setUpdatedAt(new Date());
			Address persistedAddress = addressRepo.save(address);
			org.setAddress(persistedAddress);
			return orgRepo.save(org);
		}
		throw new RuntimeException("Active Org doesnot exist");
	}



}
