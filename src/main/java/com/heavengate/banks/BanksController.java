package com.heavengate.banks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.heavengate.commons.transactions.BankAccountVerificationRequest;
import com.heavengate.commons.transactions.BankAccountVerificationResponse;
import com.heavengate.commons.transactions.BanksListRequest;
import com.heavengate.commons.transactions.BanksListResponse;
import com.heavengate.domain.Result;

@Controller
public class BanksController {
	
	@Autowired
	private BanksService banksService;
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE, value="listbank" )
	ResponseEntity<Result<List<BanksListResponse>>> getBanksList(RequestEntity<BanksListRequest> request) {
		return this.banksService.checkBanksList(request);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE, value="resolvebankaccount" )
	ResponseEntity<Result<BankAccountVerificationResponse>> verifyBank(RequestEntity<BankAccountVerificationRequest> request) {
		return this.banksService.verifyBank(request);
	}

}
