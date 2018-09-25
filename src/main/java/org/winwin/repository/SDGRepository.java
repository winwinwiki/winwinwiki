package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.SDG;

@Repository
public interface SDGRepository extends JpaRepository<SDG, Long> {

}