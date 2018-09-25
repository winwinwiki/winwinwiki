package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.Organisation;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

}