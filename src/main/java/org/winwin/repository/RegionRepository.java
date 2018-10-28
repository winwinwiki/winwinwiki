package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

}