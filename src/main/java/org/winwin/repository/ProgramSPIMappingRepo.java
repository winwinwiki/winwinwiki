package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.ProgramSPIMapping;

@Repository
public interface ProgramSPIMappingRepo extends JpaRepository<ProgramSPIMapping, Long> {

}