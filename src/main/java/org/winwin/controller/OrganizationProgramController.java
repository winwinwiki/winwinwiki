package org.winwin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.winwin.api.entity.CreateProgram;
import org.winwin.lib.AuthEncoder;
import org.winwin.model.AuditModel;
import org.winwin.model.OrgProgramMapping;
import org.winwin.model.Organization;
import org.winwin.model.ProgramModel;
import org.winwin.repository.OrgProgramMappingRepo;
import org.winwin.repository.OrganizationRepository;
import org.winwin.repository.ProgramRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrganizationProgramController {

	@Autowired
	private OrganizationRepository orgRepo;

	@Autowired
	private ProgramRepository programRepo;

	@Autowired
	private OrgProgramMappingRepo orgProgMappingRepo;

	@PostMapping("/organization/{id}/program")
	public ProgramModel createProgram(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id, @Valid @RequestBody CreateProgram request) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
		if (org != null && org.isActive()) {
			ProgramModel model = new ProgramModel();
			updateAuditFields(model, true);
			model.setIsActive(true);
			model.setName(request.getName());
			model.setDescription(request.getDescription());
			ProgramModel savedEntity = programRepo.save(model);
			OrgProgramMapping mapping = new OrgProgramMapping();
			updateAuditFields(mapping, true);
			mapping.setProgramId(savedEntity.getId());
			mapping.setOrgId(org.getId());
			mapping.setIsValid(true);
			orgProgMappingRepo.save(mapping);
			return savedEntity;
		}
		throw new RuntimeException("Active Org doesnot exist");
	}

	@GetMapping("/organization/{id}/programs")
	public List<ProgramModel> getPrograms(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
		if (org != null && org.isActive()) {
			OrgProgramMapping sample = new OrgProgramMapping();
			sample.setOrgId(id);
			sample.setIsValid(true);
			Example<OrgProgramMapping> example = Example.of(sample);
			List<OrgProgramMapping> values = orgProgMappingRepo.findAll(example);

			List<Long> programIds = new ArrayList<Long>();
			for (OrgProgramMapping mapping : values) {
				programIds.add(mapping.getProgramId());
			}
			List<ProgramModel> retValUnfiltered = programRepo.findAllById(programIds);
			List<ProgramModel> retVal = new ArrayList<ProgramModel>();
			for (ProgramModel program : retValUnfiltered) {
				if (program.getIsActive() == true) {
					retVal.add(program);
				}
			}
			return retVal;
		}
		throw new RuntimeException("Active Org doesnot exist");
	}

	@DeleteMapping("/organization/{id}/program/{progid}")
	public String deleteProgram(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue, @Valid @PathVariable("id") long id, @Valid @PathVariable("progid") long progId) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		Organization org = orgRepo.getOne(id);
		if (org != null && org.isActive()) {
			OrgProgramMapping sample = new OrgProgramMapping();
			sample.setOrgId(id);
			sample.setProgramId(progId);
			sample.setIsValid(true);
			Example<OrgProgramMapping> example = Example.of(sample);
			Optional<OrgProgramMapping> mapping = orgProgMappingRepo.findOne(example);
			if (mapping.isPresent()) {
				OrgProgramMapping mappingToBeChanged = mapping.get();
				mappingToBeChanged.setIsValid(false);
				orgProgMappingRepo.save(mappingToBeChanged);
			}
			return "OK";
		}
		throw new RuntimeException("Active Org doesnot exist");
	}

	private void updateAuditFields(AuditModel model, boolean isValid) {
		model.setCreatedAt(new Date());
		model.setUpdatedAt(new Date());
	}

}
