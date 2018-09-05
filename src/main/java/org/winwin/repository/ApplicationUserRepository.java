package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.winwin.model.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

	ApplicationUser findByEmail(String email);

}