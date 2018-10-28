package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.OrgDataSetMapping;

@Repository
public interface OrgDatasetMappingRepo extends JpaRepository<OrgDataSetMapping, Long> {

}