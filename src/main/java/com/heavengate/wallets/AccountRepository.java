package com.heavengate.wallets;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heavengate.commons.AccountStatus;
import com.heavengate.commons.AccountType;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	List<AccountEntity> findByAuthorizationid(String id);
	List<AccountEntity> findByAuthorizationidAndAccountstatus(String id, AccountStatus accountStatus);
	List<AccountEntity> findByAuthorizationidAndAccounttype(String id, AccountType accounttype);
	List<AccountEntity> findByAuthorizationidAndAccounttypeAndAccountstatus(String id, AccountType accounttype, AccountStatus accountstatus);
}
