package org.winwin.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.AccountHolder;

@Repository
public interface OrganizationRepository extends JpaRepository<AccountHolder, Long> {

	
}