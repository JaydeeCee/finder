package com.heavengate.banks;

import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.heavengate.commons.transactions.BankAccountVerificationRequest;
import com.heavengate.commons.transactions.BankAccountVerificationResponse;
import com.heavengate.commons.transactions.BanksListRequest;
import com.heavengate.commons.transactions.BanksListResponse;
import com.heavengate.commons.transactions.BvnRegistrationRequest;
import com.heavengate.commons.transactions.BvnRegistrationResponse;
import com.heavengate.domain.Result;

public interface BanksService {
	
	ResponseEntity<Result<List<BanksListResponse>>> getBanksList(RequestEntity<BanksListRequest> request);
	ResponseEntity<Result<List<BanksListResponse>>> checkBanksList(RequestEntity<BanksListRequest> request);
	
	ResponseEntity<Result<BankAccountVerificationResponse>> verifyBank(RequestEntity<BankAccountVerificationRequest> request);
	
	ResponseEntity<Result<BvnRegistrationResponse>> bvnRegistration(RequestEntity<BvnRegistrationRequest> request);
}