package com.heavengate.wallets;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.heavengate.commons.transactions.AddAccountRequest;
import com.heavengate.commons.transactions.AddAccountResponse;
import com.heavengate.commons.transactions.BvnRegistrationRequest;
import com.heavengate.commons.transactions.BvnRegistrationResponse;
import com.heavengate.domain.Result;

public interface AccountService {
	
	//ResponseEntity<Result<AddAccountResponse>> addAccount(RequestEntity<AddAccountRequest> request);
	
	//ResponseEntity<Result<BvnRegistrationResponse>> bvnRegistration(RequestEntity<BvnRegistrationRequest> request);

}
