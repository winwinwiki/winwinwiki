package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	
}