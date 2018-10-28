package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.ProgramDataSetMapping;
import org.winwin.model.ProgramSPIMapping;

@Repository
public interface ProgramDatasetMappingRepo extends JpaRepository<ProgramDataSetMapping, Long> {

}