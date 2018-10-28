package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.ProgramResourceMapping;

@Repository
public interface ProgramResourceMappingRepo extends JpaRepository<ProgramResourceMapping, Long> {

}