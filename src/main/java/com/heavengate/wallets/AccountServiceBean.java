package com.heavengate.wallets;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.heavengate.authorization.AuthorizationEntity;
import com.heavengate.authorization.AuthorizationService;
import com.heavengate.commons.AccountStatus;
import com.heavengate.commons.AccountType;
import com.heavengate.commons.AuthorizationStatus;
import com.heavengate.commons.Errorcodes;
import com.heavengate.commons.transactions.AddAccountRequest;
import com.heavengate.commons.transactions.AddAccountResponse;
import com.heavengate.commons.transactions.BankAccountVerificationRequest;
import com.heavengate.commons.transactions.BankAccountVerificationResponse;
import com.heavengate.commons.transactions.BvnRegistrationRequest;
import com.heavengate.commons.transactions.BvnRegistrationResponse;
import com.heavengate.domain.Result;
import com.heavengate.paystack.PaystackService;
import com.heavengate.utils.DateFormatter;
import com.heavengate.utils.Json;


@Service
public class AccountServiceBean implements AccountService {
	
	private Logger logger = LoggerFactory.getLogger(AccountServiceBean.class);
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@Autowired
	private PaystackService paystackService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Value("${paystack.bank.account.resolve}")
	private String bankAccountResolveUrl;
	
	
	@Value("${paystack.bvn.resolve}")
	private String bvnResolutionUrl;

	/*
	@Transactional
	@Override
	public ResponseEntity<Result<AddAccountResponse>> addAccount(RequestEntity<AddAccountRequest> request) {
		Result<AddAccountResponse> result = new Result<>();
		
		try {
			
			
			logger.debug(Json.toJson(request));
			AddAccountRequest addAccountRequest = request.getBody();
			AuthorizationEntity authorizationEntity = null;
			
			authorizationEntity  = this.authorizationService.validate(request.getHeaders());
			
			if(authorizationEntity != null && authorizationEntity.getAuthorizationstatus() == AuthorizationStatus.ACTIVE) {
				
				BankAccountVerificationRequest bankAccountVerificationRequest = new BankAccountVerificationRequest();
				bankAccountVerificationRequest.setAccountnumber(addAccountRequest.getAccountnumber());
				bankAccountVerificationRequest.setBankcode(addAccountRequest.getBankcode());
				UriComponents uriComponents = UriComponentsBuilder.newInstance().uri(new URI(bankAccountResolveUrl)).build();
				RequestEntity<BankAccountVerificationRequest> requestEntity = new RequestEntity<>(bankAccountVerificationRequest, HttpMethod.GET, uriComponents.toUri());
				ResponseEntity<Result<BankAccountVerificationResponse>> responseEntity = this.paystackService.verifyBank(requestEntity);
				
				if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
					AccountEntity accountEntity = new AccountEntity();
					accountEntity.setAccountid(RandomStringUtils.randomAlphanumeric(32));
					accountEntity.setAccountname(responseEntity.getBody().getData().getAccountname());
					accountEntity.setAccountstatus(AccountStatus.ACTIVE);
					accountEntity.setAuthorizationid(authorizationEntity.getAuthorizationid());
					accountEntity.setAccounttype(AccountType.BANK_ACCOUNT);
					accountEntity.setAccountdescription("Bank Account");
					accountEntity.setCommittedbalance(BigDecimal.ZERO);
					accountEntity.setBookbalance(BigDecimal.ZERO);
					accountEntity.setAvailablebalance(BigDecimal.ZERO);
					accountEntity.setCreationdate(DateFormatter.getDate());
					accountEntity.setAccountnumber(responseEntity.getBody().getData().getAccountnumber());
					accountEntity.setBankcode(addAccountRequest.getBankcode());
					
					accountRepository.save(accountEntity);
					
					AddAccountResponse addAccountResponse = new AddAccountResponse();
					addAccountResponse.setStatus(Boolean.TRUE);
					
					result.setData(addAccountResponse);
					result.setStatus(true);
					result.setMessage("Account successfully Added");
					
					
				} else {
					result.setErrorcode(Errorcodes.COMMUNICATION_ERROR);
					result.setErrorDescription("Unable to resolve Account number");
					result.setStatus(false);
				}
			}
			
		} catch(RestClientException | URISyntaxException ex) {
			ex.printStackTrace(System.err);
			result.setErrorcode(Errorcodes.INTERNAL_SERVER_ERROR);
			result.setErrorDescription("Internal Server Error");
			result.setStatus(false);
		}
		return ResponseEntity.ok(result);
	}
	
	
	@Transactional
	@Override
	public ResponseEntity<Result<BvnRegistrationResponse>> bvnRegistration(RequestEntity<BvnRegistrationRequest> request) {
		Result<BvnRegistrationResponse> result = new Result<>();
		
		try {
			
			AuthorizationEntity authorizationEntity = null;
			authorizationEntity = this.authorizationService.validate(request.getHeaders());
			
			if(authorizationEntity != null) {
				
				BvnRegistrationRequest bvnRegistrationRequest = request.getBody();
				String bvnUrl = bvnResolutionUrl.concat("/").concat(bvnRegistrationRequest.getBvn());
				
				UriComponents uriComponent = UriComponentsBuilder.newInstance().uri(new URI(bvnUrl))
						.build();
				
				RequestEntity<BvnRegistrationRequest> requestEntity = new RequestEntity<>(bvnRegistrationRequest, HttpMethod.POST, uriComponent.toUri());
				ResponseEntity<Result<BvnRegistrationResponse>> responseEntity = this.paystackService.genericPaystackAPIMethod(requestEntity);
				
				logger.debug(Json.toJson(responseEntity));
				 
				if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {{
						return null;
				}
				
			}
			
		}
	}
		catch(RestClientException | URISyntaxException ex) {
		ex.printStackTrace(System.err);
	}
	return ResponseEntity.ok(result);
	} */
}


