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
import org.winwin.api.entity.CreateDataSet;
import org.winwin.api.entity.CreateProgram;
import org.winwin.api.entity.CreateRegion;
import org.winwin.api.entity.CreateResource;
import org.winwin.api.entity.CreateSDG;
import org.winwin.api.entity.CreateSPI;
import org.winwin.lib.AuthEncoder;
import org.winwin.model.AuditModel;
import org.winwin.model.DataSet;
import org.winwin.model.ProgramDataSetMapping;
import org.winwin.model.ProgramModel;
import org.winwin.model.ProgramRegionMapping;
import org.winwin.model.ProgramResourceMapping;
import org.winwin.model.ProgramSDGMapping;
import org.winwin.model.ProgramSPIMapping;
import org.winwin.model.Region;
import org.winwin.model.Resource;
import org.winwin.repository.ProgramDatasetMappingRepo;
import org.winwin.repository.ProgramRegionMappingRepo;
import org.winwin.repository.ProgramRepository;
import org.winwin.repository.ProgramResourceMappingRepo;
import org.winwin.repository.ProgramSDGMappingRepo;
import org.winwin.repository.ProgramSPIMappingRepo;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProgramController {

	@Autowired
	EntityController entityController;

	@Autowired
	private ProgramRepository programRepo;

	@Autowired
	private ProgramDatasetMappingRepo programDSMappingRepo;
	
	@Autowired
	private ProgramResourceMappingRepo programResourceMappingRepo;
	
	@Autowired
	private ProgramRegionMappingRepo programRegionRepo;
	
	@Autowired
	private ProgramSDGMappingRepo programSDGRepo;

	@Autowired
	private ProgramSPIMappingRepo programSPIRepo;


	@PutMapping("/program/{id}")
	public ProgramModel updateProgram(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id, @Valid @RequestBody CreateProgram request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		ProgramModel program = programRepo.getOne(id);
		if (program != null && program.getIsActive()) {
			program.setDescription(request.getDescription());
			program.setName(request.getName());
			updateAuditFields(program);
			return programRepo.save(program);
		}
		throw new RuntimeException("Active Program doesnot exist");
	}

	@DeleteMapping("/program/{id}")
	public String inactivateProgram(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		ProgramModel program = programRepo.getOne(id);
		if (program != null) {
			updateAuditFields(program);
			program.setIsActive(false);
			programRepo.save(program);
			return "OK";
		}
		throw new RuntimeException("Active Program doesnot exist");
	}

	@PostMapping("/program/{id}/dataset")
	public DataSet createDataSetForProgram(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
			@Valid @RequestBody CreateDataSet request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		ProgramModel program = programRepo.getOne(id);
		if (program != null && program.getIsActive()) {
			DataSet ds = entityController.createDataSet(authValue, request);
			ProgramDataSetMapping mapping = new ProgramDataSetMapping();
			mapping.setIsValid(true);
			mapping.setProgramId(id);
			mapping.setDatsetId(ds.getId());
			createAuditFields(mapping);
			programDSMappingRepo.save(mapping);
			return ds;
		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	
	@PostMapping("/program/{id}/resource")
	public Resource createResourceForProgram(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
			@Valid @RequestBody CreateResource request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		ProgramModel program = programRepo.getOne(id);
		if (program != null && program.getIsActive()) {
			Resource ds = entityController.createResource(authValue,request);
			ProgramResourceMapping mapping = new ProgramResourceMapping();
			mapping.setIsValid(true);
			mapping.setProgramId(id);
			mapping.setResourceId(ds.getId());
			createAuditFields(mapping);
			programResourceMappingRepo.save(mapping);
			return ds;
		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@PostMapping("/program/{id}/region")
	public Region createRegionforProgram(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
			@Valid @RequestBody CreateRegion request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		ProgramModel program = programRepo.getOne(id);
		if (program != null && program.getIsActive()) {
			Region ds = entityController.createRegion(authValue,request);
			ProgramRegionMapping mapping = new ProgramRegionMapping();
			mapping.setIsValid(true);
			mapping.setProgramId(id);
			mapping.setRegionId(ds.getId());
			createAuditFields(mapping);
			programRegionRepo.save(mapping);
			return ds;
		}
		throw new RuntimeException("Active Program doesnot exist");
	}

	@DeleteMapping("/program/{id}/dataset/{dsId}")
	public String inactivateDataSetForProgram(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
			@Valid @PathVariable("dsId") long dsId) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		ProgramModel program = programRepo.getOne(id);
		if (program != null && program.getIsActive()) {
			ProgramDataSetMapping sample = new ProgramDataSetMapping();
			sample.setProgramId(id);
			sample.setIsValid(true);
			sample.setDatsetId(dsId);
			Example<ProgramDataSetMapping> example = Example.of(sample);
			List<ProgramDataSetMapping> values = programDSMappingRepo.findAll(example);
			for (ProgramDataSetMapping value : values) {
				value.setIsValid(false);
				updateAuditFields(value);
				programDSMappingRepo.save(value);
			}
			return "OK";
		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@DeleteMapping("/program/{id}/region/{dsId}")
	public String inactivateRegionForProgram(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
			@Valid @PathVariable("dsId") long dsId) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		ProgramModel program = programRepo.getOne(id);
		if (program != null && program.getIsActive()) {
			ProgramRegionMapping sample = new ProgramRegionMapping();
			sample.setProgramId(id);
			sample.setIsValid(true);
			sample.setRegionId(dsId);
			Example<ProgramRegionMapping> example = Example.of(sample);
			List<ProgramRegionMapping> values = programRegionRepo.findAll(example);
			for (ProgramRegionMapping value : values) {
				value.setIsValid(false);
				updateAuditFields(value);
				programRegionRepo.save(value);
			}
			return "OK";
		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@DeleteMapping("/program/{id}/resource/{dsId}")
	public String inactivateResourceForProgram(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id,
			@Valid @PathVariable("dsId") long dsId) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		ProgramModel program = programRepo.getOne(id);
		if (program != null && program.getIsActive()) {
			ProgramResourceMapping sample = new ProgramResourceMapping();
			sample.setProgramId(id);
			sample.setIsValid(true);
			sample.setResourceId(dsId);
			Example<ProgramResourceMapping> example = Example.of(sample);
			List<ProgramResourceMapping> values = programResourceMappingRepo.findAll(example);
			for (ProgramResourceMapping value : values) {
				value.setIsValid(false);
				updateAuditFields(value);
				programResourceMappingRepo.save(value);
			}
			return "OK";
		}
		throw new RuntimeException("Active Program doesnot exist");
	}

	@GetMapping("/program/{id}/dataset")
	public List<DataSet> getDataSetForProgram(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		ProgramModel program = programRepo.getOne(id);
		if (program != null && program.getIsActive()) {
			ProgramDataSetMapping sample = new ProgramDataSetMapping();
			sample.setProgramId(id);
			sample.setIsValid(true);
			Example<ProgramDataSetMapping> example = Example.of(sample);
			List<ProgramDataSetMapping> values = programDSMappingRepo.findAll(example);
			Set<Long> ids = new HashSet<Long>();
			for (ProgramDataSetMapping value : values) {
				ids.add(value.getDatsetId());
			}
			return entityController.getAllDataSetbyID(ids);

		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@GetMapping("/program/{id}/resource")
	public List<Resource> getResourceForProgram(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		ProgramModel program = programRepo.getOne(id);
		if (program != null && program.getIsActive()) {
			ProgramResourceMapping sample = new ProgramResourceMapping();
			sample.setProgramId(id);
			sample.setIsValid(true);
			Example<ProgramResourceMapping> example = Example.of(sample);
			List<ProgramResourceMapping> values = programResourceMappingRepo.findAll(example);
			Set<Long> ids = new HashSet<Long>();
			for (ProgramResourceMapping value : values) {
				ids.add(value.getResourceId());
			}
			return entityController.getAllResoursebyID(ids);

		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@GetMapping("/program/{id}/region")
	public List<Region> getRegionForProgram(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		ProgramModel program = programRepo.getOne(id);
		if (program != null && program.getIsActive()) {
			ProgramRegionMapping sample = new ProgramRegionMapping();
			sample.setProgramId(id);
			sample.setIsValid(true);
			Example<ProgramRegionMapping> example = Example.of(sample);
			List<ProgramRegionMapping> values = programRegionRepo.findAll(example);
			Set<Long> ids = new HashSet<Long>();
			for (ProgramRegionMapping value : values) {
				ids.add(value.getRegionId());
			}
			return entityController.getAllRegionById(ids);

		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@PutMapping("/program/{id}/sdg")
	public List<ProgramSDGMapping> setSDGTags(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id, @Valid @RequestBody List<CreateSDG> request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		ProgramModel program = programRepo.getOne(id);
		if (program != null && program.getIsActive()) {
			ProgramSDGMapping sample = new ProgramSDGMapping();
			sample.setProgramId(id);
			sample.setIsValid(true);
			Example<ProgramSDGMapping> example = Example.of(sample);
			List<ProgramSDGMapping> values = programSDGRepo.findAll(example);
			Set<Long> incoming = new HashSet<>();
			Set<Long> cache = new HashSet<>();
			for(CreateSDG value: request) {
				if(value.getSdgId()!= null ) {
					incoming.add(value.getSdgId());
				}
			}
			for(ProgramSDGMapping value: values) {
				if(incoming.contains(value.getSdgId()) ) {
					cache.add(value.getSdgId());
				}else {
					value.setIsValid(false);
					updateAuditFields(value);
					programSDGRepo.save(value);
				}
			}
			for(CreateSDG value: request) {
				if(value.getSdgId()!= null && !cache.contains(value.getSdgId())) {
					ProgramSDGMapping mapping = new ProgramSDGMapping();
					mapping.setIsValid(true);
					createAuditFields(mapping);
					mapping.setProgramId(id);
					mapping.setSdgId(value.getSdgId());
					mapping.setTagType(value.getTagType());
					mapping.setValue(value.getValue());
					programSDGRepo.save(mapping);
				}
			}
			return getSDGTags(authValue,id);
		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@GetMapping("/program/{id}/sdg")
	public List<ProgramSDGMapping> getSDGTags(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		ProgramModel program = programRepo.getOne(id);
		if (program != null && program.getIsActive()) {
			ProgramSDGMapping sample = new ProgramSDGMapping();
			sample.setProgramId(id);
			sample.setIsValid(true);
			Example<ProgramSDGMapping> example = Example.of(sample);
			return programSDGRepo.findAll(example);
		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@PutMapping("/program/{id}/spi")
	public List<ProgramSPIMapping> setSPITags(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id, @Valid @RequestBody List<CreateSPI> request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		ProgramModel program = programRepo.getOne(id);
		if (program != null && program.getIsActive()) {
			ProgramSPIMapping sample = new ProgramSPIMapping();
			sample.setProgramId(id);
			sample.setIsValid(true);
			Example<ProgramSPIMapping> example = Example.of(sample);
			List<ProgramSPIMapping> values = programSPIRepo.findAll(example);
			Set<Long> incoming = new HashSet<>();
			Set<Long> cache = new HashSet<>();
			for(CreateSPI value: request) {
				if(value.getSpiId()!= null ) {
					incoming.add(value.getSpiId());
				}
			}
			for(ProgramSPIMapping value: values) {
				if(incoming.contains(value.getSpiId()) ) {
					cache.add(value.getSpiId());
				}else {
					value.setIsValid(false);
					updateAuditFields(value);
					programSPIRepo.save(value);
				}
			}
			for(CreateSPI value: request) {
				if(value.getSpiId()!= null && !cache.contains(value.getSpiId())) {
					ProgramSPIMapping mapping = new ProgramSPIMapping();
					mapping.setIsValid(true);
					createAuditFields(mapping);
					mapping.setProgramId(id);
					mapping.setSpiId(value.getSpiId());
					mapping.setTagType(value.getTagType());
					mapping.setValue(value.getValue());
					programSPIRepo.save(mapping);
				}
			}
			return getSPITags(authValue, id);
		}
		throw new RuntimeException("Active Program doesnot exist");
	}
	
	@GetMapping("/program/{id}/spi")
	public List<ProgramSPIMapping> getSPITags(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		ProgramModel program = programRepo.getOne(id);
		if (program != null && program.getIsActive()) {
			ProgramSPIMapping sample = new ProgramSPIMapping();
			sample.setProgramId(id);
			sample.setIsValid(true);
			Example<ProgramSPIMapping> example = Example.of(sample);
			return programSPIRepo.findAll(example);
		}
		throw new RuntimeException("Active Program doesnot exist");
	}


	private void createAuditFields(AuditModel model) {
		model.setCreatedAt(new Date());
		model.setUpdatedAt(new Date());
	}

	private void updateAuditFields(AuditModel model) {
		model.setUpdatedAt(new Date());
	}

}
