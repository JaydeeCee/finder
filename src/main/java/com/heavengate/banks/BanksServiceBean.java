package com.heavengate.banks;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.heavengate.commons.transactions.BankAccountVerificationRequest;
import com.heavengate.commons.transactions.BankAccountVerificationResponse;
import com.heavengate.commons.transactions.BanksListRequest;
import com.heavengate.commons.transactions.BanksListResponse;
import com.heavengate.commons.transactions.BvnRegistrationRequest;
import com.heavengate.commons.transactions.BvnRegistrationResponse;
import com.heavengate.domain.Result;
import com.heavengate.paystack.PaystackService;
import com.heavengate.utils.Json;


@Service
public class BanksServiceBean implements BanksService {
	
	@Value("${paystack.list.banks}")
	private String bankListUrl;
	
	@Value("${paystack.bank.account.resolve}")
	private String bankAccountResolveUrl;
	
	@Autowired
	private PaystackService paystackService;
	
	@Autowired
	private BanksRepository banksRepository;
	
	private final Logger logger = LoggerFactory.getLogger(BanksServiceBean.class);
	
	@Override
	public ResponseEntity<Result<List<BanksListResponse>>> getBanksList(RequestEntity<BanksListRequest> request) {
		//Result<BanksListResponse> result = new Result<>();
		try {
	// 	 	 		List<BanksListResponse> banks = new ArrayList<BanksListResponse>();
		
		
		UriComponents uriComponent = UriComponentsBuilder.newInstance().uri(new URI(bankListUrl))
				.build();
		
		RequestEntity<BanksListRequest> requestEntity = new RequestEntity<>(HttpMethod.GET, uriComponent.toUri());
		
		ResponseEntity<Result<List<BanksListResponse>>> responseEntity = this.paystackService.getListOfBanks(requestEntity);
			
			if(responseEntity != null) {
				logger.debug("Response from Paystack was successful");
				logger.debug(Json.toJson(responseEntity));

				
				Result<List<BanksListResponse>> paystackResponse = responseEntity.getBody();
				
				List<BanksListResponse> bankList = paystackResponse.getData();
				bankList.forEach((list) -> {
					BanksEntity bankEntity = new BanksEntity();
					bankEntity.setName(list.getName());
					bankEntity.setSlug(list.getSlug());
					bankEntity.setCode(list.getCode());
					bankEntity.setLongcode(list.getLongcode());
					bankEntity.setCreatedAt(list.getCreatedAt());
					bankEntity.setUpdatedAt(list.getUpdatedAt());
					bankEntity.setGateway(list.getGateway());
					this.banksRepository.save(bankEntity);
				});
				
				return ResponseEntity.ok(paystackResponse);
			}
		} catch(Exception ex) {
			ex.printStackTrace(System.err);
		}
		return null;
	}
	
	@Override
	public ResponseEntity<Result<List<BanksListResponse>>> checkBanksList(RequestEntity<BanksListRequest> request) {
		
		List<BanksEntity> banksList = this.banksRepository.findAll();
		logger.debug(Json.toJson(banksList));
		
		if(banksList.isEmpty()) {
			logger.debug("Going to Paystack to fetch Bank List");
			return this.getBanksList(request);
		}
		
		List<BanksListResponse> banks= new ArrayList<>();
		
		banksList.forEach((bank) -> {
			BanksListResponse list = new BanksListResponse();
			list.setCode(bank.getCode());
			list.setLongcode(bank.getLongcode());
			list.setName(bank.getName());
			list.setSlug(bank.getSlug());
			banks.add(list);
		});
		Result<List<BanksListResponse>> result = new Result<>();
		result.setData(banks);
		result.setStatus(true);
		result.setMessage("List of Banks");
		return ResponseEntity.ok(result);
	}
	
	
	
	@Override
	public ResponseEntity<Result<BankAccountVerificationResponse>> verifyBank(RequestEntity<BankAccountVerificationRequest> request) {
		Result<BankAccountVerificationResponse> result = new Result<>();
		
		try {
			//logger.debug(Json.toJson(request));
			BankAccountVerificationRequest verificationRequest = request.getBody();
			
			UriComponents uriComponent = UriComponentsBuilder.newInstance()
					.uri(new URI(bankAccountResolveUrl))
					.queryParam("account_number", verificationRequest.getAccountnumber())
					.queryParam("bank_code", verificationRequest.getBankcode())
					.build();
			RequestEntity<BankAccountVerificationRequest> requestEntity = new RequestEntity<BankAccountVerificationRequest>(request.getBody(), HttpMethod.GET, uriComponent.toUri());
			ResponseEntity<Result<BankAccountVerificationResponse>> responseEntity = this.paystackService.verifyBank(requestEntity);
			
			if(responseEntity != null) {
			//	logger.debug("After response from Paystack");
				logger.debug(Json.toJson(responseEntity));
				return responseEntity;
			}
			
			result.setStatus(false);
			result.setMessage("Unable to resolve account information at this time");
			
		} catch(Exception ex) {
			ex.printStackTrace(System.err);
		}
		return ResponseEntity.ok(result);	
	}
	
	
	public ResponseEntity<Result<BvnRegistrationResponse>> bvnRegistration(RequestEntity<BvnRegistrationRequest> request) {
		
		Result<BvnRegistrationResponse> result = new Result<BvnRegistrationResponse>();
		
		try {
			logger.debug(Json.toJson(request));
			
			
			
		} catch(Exception ex) {
			ex.printStackTrace(System.err);
		}
		return ResponseEntity.ok(result);
	}
}
