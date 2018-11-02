package org.winwin.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.winwin.api.entity.APIAddress;
import org.winwin.api.entity.CreateDataSet;
import org.winwin.api.entity.CreateOrganization;
import org.winwin.api.entity.CreateRegion;
import org.winwin.api.entity.CreateResource;
import org.winwin.api.entity.CreateSDG;
import org.winwin.api.entity.CreateSPI;
import org.winwin.lib.AuthEncoder;
import org.winwin.model.Address;
import org.winwin.model.AuditModel;
import org.winwin.model.DataSet;
import org.winwin.model.OrgDataSetMapping;
import org.winwin.model.OrgRegionMapping;
import org.winwin.model.OrgResourceMapping;
import org.winwin.model.OrgSDGMapping;
import org.winwin.model.OrgSPIMapping;
import org.winwin.model.Organization;
import org.winwin.model.Region;
import org.winwin.model.Resource;
import org.winwin.repository.AddressRepository;
import org.winwin.repository.OrgDatasetMappingRepo;
import org.winwin.repository.OrgRegionMappingRepo;
import org.winwin.repository.OrgResourceMappingRepo;
import org.winwin.repository.OrgSDGMappingRepo;
import org.winwin.repository.OrgSPIMappingRepo;
import org.winwin.repository.OrganizationRepository;
import org.winwin.repository.RevenueRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrganizationController {
	
	@Autowired
	EntityController entityController;

	@Autowired
	private OrganizationRepository orgRepo;

	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private RevenueRepository revenueRepo;
	
	@Autowired
	private OrgSDGMappingRepo orgSDGMappingRepo;
	
	@Autowired
	private OrgSPIMappingRepo orgSPIMappingRepo;
	
	@Autowired
	private OrgDatasetMappingRepo orgDSMappingRepo;
	
	@Autowired
	private OrgResourceMappingRepo orgResourceMappingRepo;
	
	@Autowired
	private OrgRegionMappingRepo orgRegionRepo;
	
	@GetMapping("/organization/{id}")
	public Organization getOrganization(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		return orgRepo.getOne(id);
	}
	
	@PostMapping("/organization/{id}")
	public Organization createOrganization(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
										   @Valid @RequestBody CreateOrganization request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		if( id != 0 ) {
			Organization org = orgRepo.getOne(id);
			if( org == null || org.isActive() == false) {
				throw new RuntimeException("Active org does not exist");
			}
		}
		Organization org = new Organization();
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
	public Organization deleteOrganization(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
										   @Valid @RequestBody CreateOrganization request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
		if( org != null && org.isActive() ) {
			org.setUpdatedAt(new Date());
			org.setActive(false);
			return orgRepo.save(org);
		}
		throw new RuntimeException("Active Org doesnot exist");
	}
	
	@PutMapping("/organization/{id}")
	public Organization getOrganization(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
										@Valid @RequestBody CreateOrganization request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
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
	public Organization getOrganization(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
										@Valid @RequestBody APIAddress request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
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
	
	private void createAuditFields(AuditModel model) {
		model.setCreatedAt(new Date());
		model.setUpdatedAt(new Date());
	}

	private void updateAuditFields(AuditModel model) {
		model.setUpdatedAt(new Date());
	}
	
	@PutMapping("/organization/{id}/sdg")
	public List<OrgSDGMapping> setSDGTags(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id, @Valid @RequestBody List<CreateSDG> request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
		if (org != null && org.isActive()) {
			OrgSDGMapping sample = new OrgSDGMapping();
			sample.setOrgId(id);
			sample.setIsValid(true);
			Example<OrgSDGMapping> example = Example.of(sample);
			List<OrgSDGMapping> values = orgSDGMappingRepo.findAll(example);
			Set<Long> incoming = new HashSet<>();
			Set<Long> cache = new HashSet<>();
			for(CreateSDG value: request) {
				if(value.getSdgId()!= null ) {
					incoming.add(value.getSdgId());
				}
			}
			for(OrgSDGMapping value: values) {
				if(incoming.contains(value.getSdgId()) ) {
					cache.add(value.getSdgId());
				}else {
					value.setIsValid(false);
					updateAuditFields(value);
					orgSDGMappingRepo.save(value);
				}
			}
			for(CreateSDG value: request) {
				if(value.getSdgId()!= null && !cache.contains(value.getSdgId())) {
					OrgSDGMapping mapping = new OrgSDGMapping();
					mapping.setIsValid(true);
					createAuditFields(mapping);
					mapping.setOrgId(id);
					mapping.setSdgId(value.getSdgId());
					mapping.setTagType(value.getTagType());
					mapping.setValue(value.getValue());
					orgSDGMappingRepo.save(mapping);
				}
			}
			return getSDGTags(authValue,id);
		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@GetMapping("/organization/{id}/sdg")
	public List<OrgSDGMapping> getSDGTags(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
		if (org != null && org.isActive()) {
			OrgSDGMapping sample = new OrgSDGMapping();
			sample.setOrgId(id);
			sample.setIsValid(true);
			Example<OrgSDGMapping> example = Example.of(sample);
			return orgSDGMappingRepo.findAll(example);
		}
		throw new RuntimeException("Active Org doesnot exist");
	}
	
	@PutMapping("/organization/{id}/spi")
	public List<OrgSPIMapping> setSPITags(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id, @Valid @RequestBody List<CreateSPI> request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
		if (org != null && org.isActive()) {
			OrgSPIMapping sample = new OrgSPIMapping();
			sample.setOrgId(id);
			sample.setIsValid(true);
			Example<OrgSPIMapping> example = Example.of(sample);
			List<OrgSPIMapping> values = orgSPIMappingRepo.findAll(example);
			Set<Long> incoming = new HashSet<>();
			Set<Long> cache = new HashSet<>();
			for(CreateSPI value: request) {
				if(value.getSpiId()!= null ) {
					incoming.add(value.getSpiId());
				}
			}
			for(OrgSPIMapping value: values) {
				if(incoming.contains(value.getSpiId()) ) {
					cache.add(value.getSpiId());
				}else {
					value.setIsValid(false);
					updateAuditFields(value);
					orgSPIMappingRepo.save(value);
				}
			}
			for(CreateSPI value: request) {
				if(value.getSpiId()!= null && !cache.contains(value.getSpiId())) {
					OrgSPIMapping mapping = new OrgSPIMapping();
					mapping.setIsValid(true);
					createAuditFields(mapping);
					mapping.setOrgId(id);
					mapping.setSpiId(value.getSpiId());
					mapping.setTagType(value.getTagType());
					mapping.setValue(value.getValue());
					orgSPIMappingRepo.save(mapping);
				}
			}
			return getSPITags(authValue,id);
		}
		throw new RuntimeException("Active Org doesnot exist");
	}
	
	@GetMapping("/organization/{id}/spi")
	public List<OrgSPIMapping> getSPITags(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
		if (org != null && org.isActive()) {
			OrgSPIMapping sample = new OrgSPIMapping();
			sample.setOrgId(id);
			sample.setIsValid(true);
			Example<OrgSPIMapping> example = Example.of(sample);
			return orgSPIMappingRepo.findAll(example);
		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@PostMapping("/organization/{id}/dataset")
	public DataSet createDataSetForOrg(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
			@Valid @RequestBody CreateDataSet request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization program = orgRepo.getOne(id);
		if (program != null && program.isActive()) {
			DataSet ds = entityController.createDataSet(authValue, request);
			OrgDataSetMapping mapping = new OrgDataSetMapping();
			mapping.setIsValid(true);
			mapping.setOrgId(id);
			mapping.setDatsetId(ds.getId());
			createAuditFields(mapping);
			orgDSMappingRepo.save(mapping);
			return ds;
		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	
	@PostMapping("/organization/{id}/resource")
	public Resource createResourceForOrg(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
			@Valid @RequestBody CreateResource request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
		if (org != null && org.isActive()) {
			Resource ds = entityController.createResource(authValue,request);
			OrgResourceMapping mapping = new OrgResourceMapping();
			mapping.setIsValid(true);
			mapping.setOrgId(id);
			mapping.setResourceId(ds.getId());
			createAuditFields(mapping);
			orgResourceMappingRepo.save(mapping);
			return ds;
		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@PostMapping("/organization/{id}/region")
	public Region createRegionForOrg(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
			@Valid @RequestBody CreateRegion request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
		if (org != null && org.isActive()) {
			Region ds = entityController.createRegion(authValue,request);
			OrgRegionMapping mapping = new OrgRegionMapping();
			mapping.setIsValid(true);
			mapping.setOrgId(id);
			mapping.setRegionId(ds.getId());
			createAuditFields(mapping);
			orgRegionRepo.save(mapping);
			return ds;
		}
		throw new RuntimeException("Active Program doesnot exist");
	}

	@DeleteMapping("/organization/{id}/dataset/{dsId}")
	public String inactivateDataSetForOrg(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
			@Valid @PathVariable("dsId") long dsId) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
		if (org != null && org.isActive()) {
			OrgDataSetMapping sample = new OrgDataSetMapping();
			sample.setOrgId(id);
			sample.setIsValid(true);
			sample.setDatsetId(dsId);
			Example<OrgDataSetMapping> example = Example.of(sample);
			List<OrgDataSetMapping> values = orgDSMappingRepo.findAll(example);
			for (OrgDataSetMapping value : values) {
				value.setIsValid(false);
				updateAuditFields(value);
				orgDSMappingRepo.save(value);
			}
			return "OK";
		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@DeleteMapping("/organization/{id}/region/{dsId}")
	public String inactivateRegionForOrg(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
			@Valid @PathVariable("dsId") long dsId) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization program = orgRepo.getOne(id);
		if (program != null && program.isActive()) {
			OrgRegionMapping sample = new OrgRegionMapping();
			sample.setOrgId(id);
			sample.setIsValid(true);
			sample.setRegionId(dsId);
			Example<OrgRegionMapping> example = Example.of(sample);
			List<OrgRegionMapping> values = orgRegionRepo.findAll(example);
			for (OrgRegionMapping value : values) {
				value.setIsValid(false);
				updateAuditFields(value);
				orgRegionRepo.save(value);
			}
			return "OK";
		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@DeleteMapping("/organization/{id}/resource/{dsId}")
	public String inactivateResourceForOrg(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
			@Valid @PathVariable("dsId") long dsId) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
		if (org != null && org.isActive()) {
			OrgResourceMapping sample = new OrgResourceMapping();
			sample.setOrgId(id);
			sample.setIsValid(true);
			sample.setResourceId(dsId);
			Example<OrgResourceMapping> example = Example.of(sample);
			List<OrgResourceMapping> values = orgResourceMappingRepo.findAll(example);
			for (OrgResourceMapping value : values) {
				value.setIsValid(false);
				updateAuditFields(value);
				orgResourceMappingRepo.save(value);
			}
			return "OK";
		}
		throw new RuntimeException("Active Program doesnot exist");
	}

	@GetMapping("/organization/{id}/dataset")
	public List<DataSet> getDataSetForOrg(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization program = orgRepo.getOne(id);
		if (program != null && program.isActive()) {
			OrgDataSetMapping sample = new OrgDataSetMapping();
			sample.setOrgId(id);
			sample.setIsValid(true);
			Example<OrgDataSetMapping> example = Example.of(sample);
			List<OrgDataSetMapping> values = orgDSMappingRepo.findAll(example);
			Set<Long> ids = new HashSet<Long>();
			for (OrgDataSetMapping value : values) {
				ids.add(value.getDatsetId());
			}
			return entityController.getAllDataSetbyID(ids);

		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@GetMapping("/organization/{id}/resource")
	public List<Resource> getResourceForOrg(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
		if (org != null && org.isActive()) {
			OrgResourceMapping sample = new OrgResourceMapping();
			sample.setOrgId(id);
			sample.setIsValid(true);
			Example<OrgResourceMapping> example = Example.of(sample);
			List<OrgResourceMapping> values = orgResourceMappingRepo.findAll(example);
			Set<Long> ids = new HashSet<Long>();
			for (OrgResourceMapping value : values) {
				ids.add(value.getResourceId());
			}
			return entityController.getAllResoursebyID(ids);

		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@GetMapping("/organization/{id}/region")
	public List<Region> getRegionForOrg(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
		if (org != null && org.isActive()) {
			OrgRegionMapping sample = new OrgRegionMapping();
			sample.setOrgId(id);
			sample.setIsValid(true);
			Example<OrgRegionMapping> example = Example.of(sample);
			List<OrgRegionMapping> values = orgRegionRepo.findAll(example);
			Set<Long> ids = new HashSet<Long>();
			for (OrgRegionMapping value : values) {
				ids.add(value.getRegionId());
			}
			return entityController.getAllRegionById(ids);

		}
		throw new RuntimeException("Active Program doesnot exist");
	}


}
