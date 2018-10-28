package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

}