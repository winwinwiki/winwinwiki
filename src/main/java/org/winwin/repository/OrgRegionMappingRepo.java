package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.OrgRegionMapping;

@Repository
public interface OrgRegionMappingRepo extends JpaRepository<OrgRegionMapping, Long> {

}