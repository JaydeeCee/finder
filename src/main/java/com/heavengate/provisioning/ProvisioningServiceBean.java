package com.heavengate.provisioning;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;


import com.heavengate.authorization.AuthorizationEntity;
import com.heavengate.authorization.AuthorizationRepository;
import com.heavengate.authorization.AuthorizationService;
import com.heavengate.commons.AccountStatus;
import com.heavengate.commons.AccountType;
import com.heavengate.commons.ActivationRequest;
import com.heavengate.commons.ActivationResponse;
import com.heavengate.commons.AuthorizationStatus;
import com.heavengate.commons.BlockAccountRequest;
import com.heavengate.commons.BlockAccountResponse;
import com.heavengate.commons.CloseAccountRequest;
import com.heavengate.commons.CloseAccountResponse;
import com.heavengate.commons.Errorcodes;
import com.heavengate.commons.GetAccountsRequest;
import com.heavengate.commons.GetAccountsResponse;
import com.heavengate.commons.RegistrationRequest;
import com.heavengate.commons.RegistrationResponse;
import com.heavengate.commons.UnblockAccountRequest;
import com.heavengate.commons.UnblockAccountResponse;
import com.heavengate.commons.transactions.Accounts;
import com.heavengate.commons.transactions.Card;
import com.heavengate.commons.transactions.GetAccountHolderInfoRequest;
import com.heavengate.commons.transactions.GetAccountHolderInfoResponse;
import com.heavengate.commons.transactions.Money;
import com.heavengate.domain.Result;
import com.heavengate.utils.DateFormatter;
import com.heavengate.utils.Json;
import com.heavengate.wallets.AccountEntity;
import com.heavengate.wallets.AccountRepository;


@Service
public class ProvisioningServiceBean implements ProvisioningService {
	
	private final static Logger logger = LoggerFactory.getLogger(ProvisioningServiceBean.class);
	
	
	@Value("${default.currency}")
	private String defaultCurrency;
	
	@Autowired
	private AuthorizationRepository authorizationRepository;
	
	@Autowired
	private ProvisioningRepository provisioningRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorizationService authorizationService;
	
	
	// register new account
	
	@Transactional
	@Override 	
	public ResponseEntity<Result<RegistrationResponse>> registration(RequestEntity<RegistrationRequest> request) {
		
		Result<RegistrationResponse> result = new Result<>();
		
		try {
			logger.debug(Json.toJson(request));
			
			RegistrationRequest registrationRequest = request.getBody();
			
			
			
			// create authorization
			AuthorizationEntity authorizationEntity = this.authorizationRepository.findByEmailaddress(registrationRequest.getEmailaddress());
			
			if(authorizationEntity != null) {
				authorizationEntity = this.authorizationRepository.findByMobilenumber(registrationRequest.getMobilenumber());
			}
			
			if(registrationRequest.getFirstname() != null && registrationRequest.getLastname() != null && registrationRequest.getMiddlename() != null &&  registrationRequest.getMobilenumber() != null && registrationRequest.getEmailaddress() != null
					&& registrationRequest.getAddress() != null && registrationRequest.getAuthorizationtype() != null && registrationRequest.getBirthmonth() != null && registrationRequest.getBirthyear() != null) {
			if(authorizationEntity == null) {
				authorizationEntity = new AuthorizationEntity();
				authorizationEntity.setActivationtoken(RandomStringUtils.randomNumeric(8));
				authorizationEntity.setAuthorizationid(RandomStringUtils.randomNumeric(32));
				authorizationEntity.setAuthorizationstatus(AuthorizationStatus.REGISTERED);
				authorizationEntity.setAuthorizationtype(registrationRequest.getAuthorizationtype());
				authorizationEntity.setEmailaddress(registrationRequest.getEmailaddress());
				authorizationEntity.setMobilenumber(registrationRequest.getMobilenumber());
				authorizationEntity.setCreationdate(DateFormatter.getDate());
				
				if(registrationRequest.getUsername() != null) {
					authorizationEntity.setUsername(registrationRequest.getUsername());
				} else {
					authorizationEntity.setUsername(registrationRequest.getMobilenumber());
				}
				
				this.authorizationRepository.save(authorizationEntity);
				
				
				// create account holder
				AccountHolderEntity accountHolder = new AccountHolderEntity();
				accountHolder.setAccountholderid(RandomStringUtils.randomNumeric(32));
				accountHolder.setAuthorizationid(authorizationEntity.getAuthorizationid());
				accountHolder.setAddress(registrationRequest.getAddress());
				accountHolder.setFirstname(registrationRequest.getFirstname());
				accountHolder.setLastname(registrationRequest.getLastname());
				accountHolder.setMiddlename(registrationRequest.getMiddlename());
				accountHolder.setBirthmonth(registrationRequest.getBirthmonth());
				accountHolder.setBirthyear(registrationRequest.getBirthyear());
				accountHolder.setRegistrationdate(authorizationEntity.getCreationdate());
				
				this.provisioningRepository.save(accountHolder);
				
				
				
				// create accounts - Main Account 
				AccountEntity account = new AccountEntity();
				
				account.setAccountid(RandomStringUtils.randomNumeric(32));
				account.setAccountname(registrationRequest.getEmailaddress());
				account.setAccountdescription("Main Account");
				account.setAccountstatus(AccountStatus.CREATED);
				account.setAccounttype(AccountType.MAIN_ACCOUNT);
				account.setAvailablebalance(BigDecimal.ZERO);
				account.setAuthorizationid(authorizationEntity.getAuthorizationid());
				account.setBookbalance(BigDecimal.ZERO);
				account.setCommittedbalance(BigDecimal.ZERO);
				account.setBookbalance(BigDecimal.ZERO);
				account.setCurrency(defaultCurrency);
				account.setCreationdate(authorizationEntity.getCreationdate());
				
				accountRepository.save(account);
					
				
				// create Accounts - Savings Account
				account = new AccountEntity();
				
				account.setAccountid(RandomStringUtils.randomNumeric(32));
				account.setAccountname(registrationRequest.getEmailaddress());
				account.setAccountdescription("Savings Account");
				account.setAccountstatus(AccountStatus.CREATED);
				account.setAccounttype(AccountType.SAVINGS);
				account.setAvailablebalance(BigDecimal.ZERO);
				account.setAuthorizationid(authorizationEntity.getAuthorizationid());
				account.setBookbalance(BigDecimal.ZERO);
				account.setCommittedbalance(BigDecimal.ZERO);
				account.setBookbalance(BigDecimal.ZERO);
				account.setCurrency(defaultCurrency);
				account.setCreationdate(authorizationEntity.getCreationdate());
				
				accountRepository.save(account);
				
				// Return response
				RegistrationResponse registrationResponse = new RegistrationResponse();
				
				registrationResponse.setAccountHolderId(accountHolder.getAccountholderid());
				registrationResponse.setAuthorizationId(accountHolder.getAuthorizationid());
				
				result.setStatus(true);
				result.setMessage("Registration successfully completed");
				result.setData(registrationResponse);
				
			} else {
				result.setStatus(false);
				result.setMessage("Account already exists.");
			}
			} else  {
				result.setStatus(false);
				result.setErrorcode(Errorcodes.INCOMPLETE_REQUEST_PARAMETERS);
			}
		} catch(RestClientException ex) {
			ex.printStackTrace(System.err);
			result.setStatus(false);
			result.setErrorcode(Errorcodes.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(result);
	}
	

	
	// Activate newly Registered Account
	@Transactional
	@Override
	public ResponseEntity<Result<ActivationResponse>> activation(RequestEntity<ActivationRequest> request) {
		Result<ActivationResponse> result = new Result<>();
		
		try {
			
			logger.debug(Json.toJson(request));
			ActivationRequest activationRequest = request.getBody();
			logger.debug(Json.toJson(activationRequest));
			// verify activation token
			AuthorizationEntity authorizationEntity = authorizationRepository.findByActivationtoken(activationRequest.getActivationToken());
			if(authorizationEntity != null && authorizationEntity.getAuthorizationstatus() == AuthorizationStatus.REGISTERED) {
				if(activationRequest.getPassword().equals(activationRequest.getConfirmPassword())) {
					authorizationEntity.setActivationtoken(null);
					authorizationEntity.setAuthorizationstatus(AuthorizationStatus.ACTIVE);
					authorizationEntity.setLastupdatedate(Calendar.getInstance().getTime());
					authorizationEntity.setPassword(passwordEncoder.encode(activationRequest.getPassword()));
					authorizationRepository.save(authorizationEntity);
					
					ActivationResponse activationResponse = new ActivationResponse();
					activationResponse.setActivationStatus(Boolean.TRUE);
					result.setStatus(Boolean.TRUE);
					result.setMessage("Activation successful");
					result.setData(activationResponse);
					
					logger.debug(Json.toJson(activationResponse));
					
				} else {
					result.setStatus(false);
					result.setErrorcode(Errorcodes.PASSWORD_MISMATCH);
				}
			} else {
					result.setStatus(false);
					result.setErrorcode(Errorcodes.ACCOUNT_ALREADY_ACTIVE);
			}
		} catch(RestClientException ex) {
			ex.printStackTrace(System.err);
			
		}
		return ResponseEntity.ok(result);
	}
	
	
	// block a user account
	@Transactional
	@Override
	public ResponseEntity<Result<BlockAccountResponse>> blockAccount(RequestEntity<BlockAccountRequest> request) {
		Result<BlockAccountResponse> result = new Result<>();
		
		try {
			
			logger.debug(Json.toJson(request));
			
			BlockAccountRequest blockAccountRequest = request.getBody();
			AuthorizationEntity requestingAuthorizationEntity = null;
			logger.debug("going inside validate method");
			requestingAuthorizationEntity = this.authorizationService.validate(request.getHeaders());
			
			AuthorizationEntity authorizationEntity = null;
			
			if(blockAccountRequest.getEmailaddress() == null && blockAccountRequest.getMobilenumber() == null && blockAccountRequest.getUsername() == null) {
				requestingAuthorizationEntity = authorizationEntity;
			} else {
				authorizationEntity = this.authorizationRepository.findByEmailaddress(blockAccountRequest.getEmailaddress());
				if(authorizationEntity == null) {
					authorizationEntity = this.authorizationRepository.findByMobilenumber(blockAccountRequest.getMobilenumber());
					if(authorizationEntity == null) {
						authorizationEntity = this.authorizationRepository.findByUsername(blockAccountRequest.getUsername());
					}
				}
			}
			
			// make sure request auth is same as auth
			if(authorizationEntity != null && requestingAuthorizationEntity != null) {
				if(requestingAuthorizationEntity.getAuthorizationid().equals(authorizationEntity.getAuthorizationid())) {
					
					if(authorizationEntity.getAuthorizationstatus() == AuthorizationStatus.ACTIVE && authorizationEntity.getAuthorizationstatus() != AuthorizationStatus.CLOSED) {
						
						// block authorization
						authorizationEntity.setAuthorizationstatus(AuthorizationStatus.BLOCKED);
						authorizationEntity.setLastlogindate(Calendar.getInstance().getTime());
						
						authorizationRepository.save(authorizationEntity);
						
						
						// block accounts
						List<AccountEntity> accountEntities = this.accountRepository.findByAuthorizationid(authorizationEntity.getAuthorizationid());
						accountEntities.stream().map((account) -> {
							account.setAccountstatus(AccountStatus.BLOCKED);
							return account;
						}).map((account) -> {
							account.setLastupdateddate(Calendar.getInstance().getTime());
							return account;
						}).forEachOrdered((account) -> {
							accountRepository.save(account);
						});
						
						
						// response
						BlockAccountResponse blockAccountResponse = new BlockAccountResponse();
						blockAccountResponse.setStatus(true);
						
						result.setStatus(true);
						result.setData(blockAccountResponse);
						
						logger.debug(Json.toJson(result));
						
					} else {
						result.setStatus(false);
						result.setErrorcode(Errorcodes.AUTHORIZATION_NOT_ACTIVE);
					}
				} else {
					result.setStatus(false);
					result.setErrorcode(Errorcodes.OPERATION_FORBIDDEN);
				}
			} else {
					result.setErrorcode(Errorcodes.AUTHORIZATION_NOT_FOUND);
			}
 			
		} catch(RestClientException ex) {
			result.setStatus(false);
			result.setErrorcode(Errorcodes.INTERNAL_SERVER_ERROR);
			ex.printStackTrace(System.err);
		}
		return ResponseEntity.ok(result);
	}
	
	
	// close account
	@Override
	@Transactional
	public ResponseEntity<Result<CloseAccountResponse>> closeAccount(RequestEntity<CloseAccountRequest> request) {
		Result<CloseAccountResponse> result = new Result<>();
		

		try {
		logger.debug(Json.toJson(request));
		
		CloseAccountRequest closeAccountRequest = request.getBody();
		
		AuthorizationEntity requestingAuthorizationEntity = null;
		AuthorizationEntity authorizationEntity = null;
		
		requestingAuthorizationEntity = this.authorizationService.validate(request.getHeaders());
		
		if(closeAccountRequest.getAccountid() ==  null && closeAccountRequest.getEmailaddress() == null && closeAccountRequest.getMobilenumber() == null) {
			
			authorizationEntity = requestingAuthorizationEntity;
			
		} else {
			authorizationEntity = this.authorizationRepository.findByEmailaddress(closeAccountRequest.getEmailaddress());
			if(authorizationEntity == null) {
				authorizationEntity = this.authorizationRepository.findByMobilenumber(closeAccountRequest.getMobilenumber());
				if(authorizationEntity == null) {
					authorizationEntity = this.authorizationRepository.findByUsername(closeAccountRequest.getUsername());
				}
			}
		}
		
		
		// confirm auth is auth
		if(authorizationEntity != null && requestingAuthorizationEntity != null) {
			if(authorizationEntity.getAuthorizationid().equals(requestingAuthorizationEntity.getAuthorizationid())) {
				if(authorizationEntity.getAuthorizationstatus() != AuthorizationStatus.CLOSED) {
					
					// close Authorization
					authorizationEntity.setAuthorizationstatus(AuthorizationStatus.CLOSED);
					authorizationEntity.setLastupdatedate(Calendar.getInstance().getTime());
					authorizationRepository.save(authorizationEntity);
					
					// close Accounts
					List<AccountEntity> accountEntities = this.accountRepository.findByAuthorizationid(authorizationEntity.getAuthorizationid());
					accountEntities.stream().map((account) -> {
						account.setAccountstatus(AccountStatus.CLOSED);
						return account;
					}).map((account) -> {
						account.setLastupdateddate(Calendar.getInstance().getTime());
						return account;
					}).forEachOrdered((account) -> {
						this.accountRepository.save(account);
					});
					
					
					// response
					CloseAccountResponse closeAccountResponse = new CloseAccountResponse();
					
					closeAccountResponse.setStatus(Boolean.TRUE);
					result.setData(closeAccountResponse);
					result.setStatus(true);
					result.setMessage("Account successfully closed");
				} else {
					result.setErrorcode(Errorcodes.AUTHORIZATION_NOT_ACTIVE);
					result.setStatus(false);
				}
			} else {
				result.setStatus(false);
				result.setErrorcode(Errorcodes.OPERATION_FORBIDDEN);
			}
		} else {
			result.setStatus(false);
			result.setErrorcode(Errorcodes.AUTHORIZATION_NOT_FOUND);
		}			
		} catch(RestClientException ex) {
			ex.printStackTrace(System.err);
			result.setStatus(Boolean.FALSE);
			result.setErrorcode(Errorcodes.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(result);
	}
	
	
	
	@Override
	public ResponseEntity<Result<GetAccountsResponse>> getAccounts(RequestEntity<GetAccountsRequest> request) {
		Result<GetAccountsResponse> result = new Result<>();
		
		AuthorizationEntity requestingAuthorizationEntity = null;
		List<Accounts> accounts = new ArrayList<Accounts>(); 
		
		requestingAuthorizationEntity = this.authorizationService.validate(request.getHeaders());
		if(requestingAuthorizationEntity != null) {
			
			List<AccountEntity> accountEntities = this.accountRepository.findByAuthorizationid(requestingAuthorizationEntity.getAuthorizationid());
			accountEntities.forEach((accountEntity) -> {
				Accounts account = new Accounts();
				account.setAccountidentity(accountEntity.getAccountid());
				account.setAccountstatus(accountEntity.getAccountstatus());
				account.setAccounttype(accountEntity.getAccounttype());
				account.setAvailablebalance(new Money());
				
				account.getAvailablebalance().setAmount(accountEntity.getAvailablebalance());
				account.getAvailablebalance().setCurrency(accountEntity.getCurrency());
				
				if(accountEntity.getAccounttype() == AccountType.CARD) {
					account.setCard(new Card());
					account.getCard().setCardtype(accountEntity.getCardtype());
					account.getCard().setExpirymonth(accountEntity.getExpirymonth());
					account.getCard().setExpiryyear(accountEntity.getExpiryyear());
					account.getCard().setLast4(accountEntity.getLast4figures());
				}
				
				accounts.add(account);
			});
			
			GetAccountsResponse getAccountsResponse = new GetAccountsResponse();
			
			getAccountsResponse.setAccounts(accounts);
			result.setData(getAccountsResponse);
			result.setMessage("List of Accounts");
			result.setStatus(Boolean.TRUE);
			
		} else {
			result.setStatus(false);
			result.setErrorcode(Errorcodes.AUTHORIZATION_NOT_FOUND);
		}
		return ResponseEntity.ok(result);
	}
	
	
	
	
	
	// unblock user Account
	 public ResponseEntity<Result<UnblockAccountResponse>> unblockAccount(RequestEntity<UnblockAccountRequest> request) {
		 
		 Result<UnblockAccountResponse> result = new Result<>();
		 
		 try {
			 logger.debug(Json.toJson(request.getBody()));
			 
			 UnblockAccountRequest unblockAccountRequest = request.getBody();
			 AuthorizationEntity requestingAuthorizationEntity = null;
			 AuthorizationEntity authorizationEntity = null;
			 
			 requestingAuthorizationEntity = this.authorizationService.validate(request.getHeaders());
			 
			 if((unblockAccountRequest.getEmailaddress() == null || unblockAccountRequest.getMobilenumber() == null || unblockAccountRequest.getUsername() == null)  && (unblockAccountRequest.getPassword() == null)) {
				 authorizationEntity = requestingAuthorizationEntity;
			 } else {
				 authorizationEntity = this.authorizationRepository.findByEmailaddress(unblockAccountRequest.getEmailaddress());
				 if(authorizationEntity == null) {
					 authorizationEntity = this.authorizationRepository.findByMobilenumber(unblockAccountRequest.getMobilenumber());
					 if(authorizationEntity == null) {
						 authorizationEntity = this.authorizationRepository.findByUsername(unblockAccountRequest.getUsername());
					 }
				 }
			 }
			 
			 // ensure requestingAuth is same as authEntity to be unblocked
			 if(authorizationEntity != null && requestingAuthorizationEntity != null) {
				 if((authorizationEntity.getAuthorizationid().equals(requestingAuthorizationEntity.getAuthorizationid())) && (this.passwordEncoder.matches(unblockAccountRequest.getPassword(), authorizationEntity.getPassword()))) {
					 if(authorizationEntity.getAuthorizationstatus() == AuthorizationStatus.BLOCKED) {
						 authorizationEntity.setAuthorizationstatus(AuthorizationStatus.ACTIVE);
						 authorizationEntity.setLastupdatedate(Calendar.getInstance().getTime());
						 this.authorizationRepository.save(authorizationEntity);
						 
						 // unblock all accounts
						 List<AccountEntity>accountEntities = this.accountRepository.findByAuthorizationid(authorizationEntity.getAuthorizationid());
						 accountEntities.stream().map((accountEntity) -> {
							 accountEntity.setAccountstatus(AccountStatus.ACTIVE);
							 return accountEntity;
						 }).map((accountEntity) -> {
							 accountEntity.setLastupdateddate(Calendar.getInstance().getTime());
							 return accountEntity;
						 }).forEachOrdered((accountEntity) -> {
							 this.accountRepository.save(accountEntity);
						 });
						 
						 
						 // response
						 UnblockAccountResponse unblockAccountResponse = new UnblockAccountResponse();
						 
						 unblockAccountResponse.setStatus(true);
						 result.setData(unblockAccountResponse);
						 result.setStatus(true);
						 result.setMessage("Account Unblock operation successful");
					 } else {
						 result.setStatus(false);
						 result.setErrorcode(Errorcodes.ACCOUNT_ALREADY_ACTIVE);
					 }
				 } else {
					 result.setStatus(false);
					 result.setErrorcode(Errorcodes.OPERATION_FORBIDDEN);
				 }
			 } else {
				 result.setStatus(false);
				 result.setErrorcode(Errorcodes.AUTHORIZATION_NOT_FOUND);
			 }
			 
			 
		 } catch(RestClientException ex) {
			 result.setErrorcode(Errorcodes.INTERNAL_SERVER_ERROR);
			 result.setStatus(false);
			 ex.printStackTrace(System.err);
		 }
		 return ResponseEntity.ok(result);
	 }
	 
	 
	 
	 // Get Account Holder Info
	@Override
	public ResponseEntity<Result<GetAccountHolderInfoResponse>> getAccountholderInfo(RequestEntity<GetAccountHolderInfoRequest> request) {
		Result<GetAccountHolderInfoResponse> result = new Result<>();
		
		try {
			
			logger.debug(Json.toJson(request));
			
			AuthorizationEntity authorizationEntity = null;
			 authorizationEntity = this.authorizationService.validate(request.getHeaders());
			 
			 AccountHolderEntity accountHolderEntity = this.provisioningRepository.findByAuthorizationid(authorizationEntity.getAuthorizationid());
			 
			 GetAccountHolderInfoResponse accountHolderInfoResponse = new GetAccountHolderInfoResponse();
			 BeanUtils.copyProperties(accountHolderEntity, accountHolderInfoResponse);
			 BeanUtils.copyProperties(authorizationEntity, accountHolderInfoResponse);
			 //accountHolderInfoResponse.setAccountholderid(accountHolderEntity.getAccountholderid());
			 //accountHolderInfoResponse.setAuthorizationid(accountHolderEntity.getAuthorizationid());
			 
			 result.setData(accountHolderInfoResponse);
			 result.setStatus(true);
			 result.setMessage("Account Holder Information");
			 
					
			
		} catch(RestClientException | BeansException ex) {
			result.setStatus(false);
			result.setErrorcode(Errorcodes.INTERNAL_SERVER_ERROR);
			ex.printStackTrace(System.err);
		}
		return ResponseEntity.ok(result);
	}
	
	
	
	
}
