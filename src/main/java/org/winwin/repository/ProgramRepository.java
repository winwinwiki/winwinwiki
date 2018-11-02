package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.ProgramModel;

@Repository
public interface ProgramRepository extends JpaRepository<ProgramModel, Long> {

}