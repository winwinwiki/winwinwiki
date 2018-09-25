package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}