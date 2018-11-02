package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.ProgramRegionMapping;

@Repository
public interface ProgramRegionMappingRepo extends JpaRepository<ProgramRegionMapping, Long> {

}