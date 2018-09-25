package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.SPI;

@Repository
public interface SPIRepository extends JpaRepository<SPI, Long> {

}