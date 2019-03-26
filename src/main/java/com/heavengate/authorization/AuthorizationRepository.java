package com.heavengate.authorization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationRepository extends JpaRepository<AuthorizationEntity, Long> {
	AuthorizationEntity findByEmailaddress(String emailAddress);
	AuthorizationEntity findByMobilenumber(String mobileNumber);
	AuthorizationEntity findByActivationtoken(String activationToken);
	AuthorizationEntity findByAuthorizationid(String authorizationid);
	AuthorizationEntity findByUsername(String username);
}
