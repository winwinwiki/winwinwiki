package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.OrgProgramMapping;

@Repository
public interface OrgProgramMappingRepo extends JpaRepository<OrgProgramMapping, Long> {

}