package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.OrgSPIMapping;

@Repository
public interface OrgSPIMappingRepo extends JpaRepository<OrgSPIMapping, Long> {

}