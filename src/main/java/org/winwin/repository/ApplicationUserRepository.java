package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.winwin.model.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

	ApplicationUser findByUsername(String userName);

	ApplicationUser findByEmail(String userName);

}