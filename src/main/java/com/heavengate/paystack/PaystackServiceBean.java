package com.heavengate.paystack;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.heavengate.commons.Errorcodes;
import com.heavengate.commons.paystack.BvnVerificationRequest;
import com.heavengate.commons.paystack.BvnVerificationResponse;
import com.heavengate.commons.transactions.BankAccountVerificationRequest;
import com.heavengate.commons.transactions.BankAccountVerificationResponse;
import com.heavengate.commons.transactions.BanksListRequest;
import com.heavengate.commons.transactions.BanksListResponse;
import com.heavengate.domain.Result;
import com.heavengate.utils.Json;


@Service
public class PaystackServiceBean  implements PaystackService {
	
	private Logger logger = LoggerFactory.getLogger(PaystackServiceBean.class);
	
	private RestTemplate restTemplate = new RestTemplate();
	
	
	@Value("${paystack.authorization.key}")
	private String authorizationKey;
	

	
	
	
	// generic Paystack Method
	@Override
	public ResponseEntity<Result<List<BanksListResponse>>> getListOfBanks(RequestEntity<BanksListRequest> request) {
			
		
		try {
		logger.debug(Json.toJson(request));
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.add("Authorization", "Bearer " + authorizationKey);
		
		HttpEntity<BanksListRequest> httpEntity = new HttpEntity<>(request.getBody(), httpHeader);
		
		
		
		ResponseEntity<Result<List<BanksListResponse>>> responseEntity = this.restTemplate.exchange(request.getUrl().toString(), request.getMethod(), httpEntity, new ParameterizedTypeReference<Result<List<BanksListResponse>>>() {
			
		});
		logger.debug(Json.toJson(responseEntity));
		if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
			return responseEntity;
		} else {
			Result<List<BanksListResponse>> response = new Result<>();
			response.setStatus(false);
			response.setMessage("API Request Failure");
			return ResponseEntity.ok(response);
		}
		
		} catch(RestClientException ex) {
			Result<List<BanksListResponse>> response = new Result<>();
			response.setStatus(false);
			response.setErrorcode(Errorcodes.INTERNAL_SERVER_ERROR);
			ex.printStackTrace(System.err);
			return ResponseEntity.ok(response);
		}
		
		
		
	} 
	
	@Override
	public ResponseEntity<Result<BankAccountVerificationResponse>> verifyBank(RequestEntity<BankAccountVerificationRequest> request) {
		
		try {
		logger.debug(Json.toJson(request));
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.add("Authorization", "Bearer " + authorizationKey);
		HttpEntity<BankAccountVerificationRequest> httpEntity = new HttpEntity<>(httpHeader);
		logger.debug(Json.toJson(httpEntity));
		
		ResponseEntity<Result<BankAccountVerificationResponse>> responseEntity = this.restTemplate.exchange(request.getUrl().toString(), request.getMethod(), httpEntity, new ParameterizedTypeReference<Result<BankAccountVerificationResponse>>() {
			
		});
		
		if(responseEntity != null) {
			return responseEntity;
		}
		
		
		} catch(Exception ex) {
			ex.printStackTrace(System.err);
			Result<BankAccountVerificationResponse> result = new Result<BankAccountVerificationResponse>();
			result.setStatus(false);
			result.setMessage("Unable to resolve account information at this time");
			return ResponseEntity.ok(result);
		}
		return null; 
	}
	
	
	@Override
	public ResponseEntity<Result<BvnVerificationResponse>> bvnVerification(RequestEntity<BvnVerificationRequest> request) {
		
		try {
		 	
			logger.debug(Json.toJson(request));
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.add("Authorization", "Bearer " + authorizationKey);
			HttpEntity<BvnVerificationRequest> httpEntity = new HttpEntity<>(request.getBody(), httpHeader);
			ResponseEntity<Result<BvnVerificationResponse>> responseEntity = this.restTemplate.exchange(request.getUrl().toString(), request.getMethod(), httpEntity, new ParameterizedTypeReference<Result<BvnVerificationResponse>>() {
				
			});
			
			if(responseEntity != null) {
				return responseEntity;
			}
			
		} catch(Exception ex) {
			Result<BvnVerificationResponse> result = new Result<>();
			result.setStatus(false);
			result.setMessage("Unable to resolve Bvn Verification at this time");
			return ResponseEntity.ok(result);
		}
		return null;
	}
}
