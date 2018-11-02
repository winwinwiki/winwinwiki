package org.winwin.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.winwin.api.entity.CreateDataSet;
import org.winwin.api.entity.CreateRegion;
import org.winwin.api.entity.CreateResource;
import org.winwin.lib.AuthEncoder;
import org.winwin.model.AuditModel;
import org.winwin.model.DataSet;
import org.winwin.model.Region;
import org.winwin.model.Resource;
import org.winwin.repository.DataSetRepository;
import org.winwin.repository.RegionRepository;
import org.winwin.repository.ResourceRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EntityController {

	@Autowired
	RegionRepository regionRepo;

	@Autowired
	DataSetRepository datsetRepo;

	@Autowired
	ResourceRepository resourceRepo;

	@PostMapping("/dataset")
	public DataSet createDataSet(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @RequestBody CreateDataSet request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		DataSet r = new DataSet();
		r.setDescription(request.getDescription());
		r.setName(request.getName());
		r.setType(request.getType());
		r.setUrl(request.getUrl());
		createAuditFields(r);
		return datsetRepo.save(r);
	}

	@PutMapping("/dataset/{id}")
	public DataSet updateDataSet(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id, @Valid @RequestBody CreateDataSet request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		DataSet r = datsetRepo.getOne(id);
		if (r != null) {
			r.setDescription(request.getDescription());
			r.setName(request.getName());
			r.setType(request.getType());
			r.setUrl(request.getUrl());
			updateAuditFields(r);
			return datsetRepo.save(r);
		}
		throw new RuntimeException("Dataset doesnot exist");
	}

	@GetMapping("/dataset/{id}")
	public DataSet getDatset(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue,@Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		return datsetRepo.getOne(id);
	}

	@GetMapping("/dataset")
	public List<DataSet> searchDataset(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "url", required = false) String url) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		DataSet spec = new DataSet();

		spec.setName(name);
		spec.setType(type);
		spec.setUrl(url);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues()
				.withMatcher("name", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING))
				.withMatcher("type", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING))
				.withMatcher("url", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING));
		Example<DataSet> example = Example.of(spec, matcher);
		return datsetRepo.findAll(example);
	}
	
	public List<DataSet> getAllDataSetbyID(Iterable<Long> ids){
		return datsetRepo.findAllById(ids);
	}
	
	public List<Resource> getAllResoursebyID(Iterable<Long> ids){
		return resourceRepo.findAllById(ids);
	}

	public List<Region> getAllRegionById(Iterable<Long> ids){
		return regionRepo.findAllById(ids);
	}


	@PostMapping("/resource")
	public Resource createResource(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @RequestBody CreateResource request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Resource r = new Resource();
		r.setDescription(request.getDescription());
		r.setCount(request.getCount());
		r.setCategory(request.getCategory());
		createAuditFields(r);
		return resourceRepo.save(r);
	}

	@PutMapping("/resource/{id}")
	public Resource updateResource(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id, @Valid @RequestBody CreateResource request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Resource r = resourceRepo.getOne(id);
		if (r != null) {
			r.setDescription(request.getDescription());
			r.setCount(request.getCount());
			r.setCategory(request.getCategory());
			updateAuditFields(r);
			return resourceRepo.save(r);
		}
		throw new RuntimeException("Resource doesnot exist");
	}

	@GetMapping("/resource/{id}")
	public Resource getResource(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		return resourceRepo.getOne(id);
	}

	@GetMapping("/resource")
	public List<Resource> searchResource(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "url", required = false) String url) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Resource spec = new Resource();

		spec.setCategory(category);
		spec.setDescription(description);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues()
				.withMatcher("category",
						ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING))
				.withMatcher("description",
						ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING));
		Example<Resource> example = Example.of(spec, matcher);
		return resourceRepo.findAll(example);
	}

	@PostMapping("/region")
	public Region createRegion(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @RequestBody CreateRegion request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Region r = new Region();
		r.setCity(request.getCity());
		r.setCountry(request.getCountry());
		r.setState(request.getState());
		createAuditFields(r);
		return regionRepo.save(r);
	}

	@PutMapping("/region/{id}")
	public Region updateRegion(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id, @Valid @RequestBody CreateRegion request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Region r = regionRepo.getOne(id);
		if (r != null) {
			r.setCity(request.getCity());
			r.setCountry(request.getCountry());
			r.setState(request.getState());
			updateAuditFields(r);
			return regionRepo.save(r);
		}
		throw new RuntimeException("Region doesnot exist");
	}

	@GetMapping("/region/{id}")
	public Region getRegion(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		return regionRepo.getOne(id);
	}

	@GetMapping("/region")
	public List<Region> getRegion(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "country", required = false) String country) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Region regionSpec = new Region();

		regionSpec.setCity(city);
		regionSpec.setCountry(country);
		regionSpec.setState(state);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues()
				.withMatcher("city", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING))
				.withMatcher("country",
						ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING))
				.withMatcher("state",
						ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING));
		Example<Region> example = Example.of(regionSpec, matcher);
		return regionRepo.findAll(example);
	}

	private void createAuditFields(AuditModel model) {
		model.setCreatedAt(new Date());
		model.setUpdatedAt(new Date());
	}

	private void updateAuditFields(AuditModel model) {
		model.setUpdatedAt(new Date());
	}
}
