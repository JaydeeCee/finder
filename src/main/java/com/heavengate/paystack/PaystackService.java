package com.heavengate.paystack;


import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.heavengate.commons.paystack.BvnVerificationRequest;
import com.heavengate.commons.paystack.BvnVerificationResponse;
import com.heavengate.commons.paystack.CreateTransferRecipientRequest;
import com.heavengate.commons.paystack.CreateTransferRecipientResponse;
import com.heavengate.commons.transactions.BankAccountVerificationRequest;
import com.heavengate.commons.transactions.BankAccountVerificationResponse;
import com.heavengate.commons.transactions.BanksListRequest;
import com.heavengate.commons.transactions.BanksListResponse;
import com.heavengate.domain.Result;


public interface PaystackService {
	
	/*ResponseEntity<Result<BvnVerificationResponse>> bvnVerification(RequestEntity<BvnVerificationRequest> request);
	
	ResponseEntity<Result<CreateTransferRecipientResponse>>createTransferRecipient(RequestEntity<CreateTransferRecipientRequest> request); */
	
	ResponseEntity<Result<List<BanksListResponse>>> getListOfBanks(RequestEntity<BanksListRequest> request);
	
	//ResponseEntity<Result<BankAccountVerificationResponse>> verifyBank(RequestEntity<BankAccountVerificationRequest> request);

	ResponseEntity<Result<BankAccountVerificationResponse>> verifyBank(RequestEntity<BankAccountVerificationRequest> requestEntity);

	ResponseEntity<Result<BvnVerificationResponse>> bvnVerification(RequestEntity<BvnVerificationRequest> request);
}
