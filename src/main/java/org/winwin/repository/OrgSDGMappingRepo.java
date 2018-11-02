package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.OrgSDGMapping;

@Repository
public interface OrgSDGMappingRepo extends JpaRepository<OrgSDGMapping, Long> {

}