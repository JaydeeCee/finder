package com.heavengate.provisioning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.heavengate.commons.ActivationRequest;
import com.heavengate.commons.ActivationResponse;
import com.heavengate.commons.BlockAccountRequest;
import com.heavengate.commons.BlockAccountResponse;
import com.heavengate.commons.CloseAccountRequest;
import com.heavengate.commons.CloseAccountResponse;
import com.heavengate.commons.GetAccountsRequest;
import com.heavengate.commons.GetAccountsResponse;
import com.heavengate.commons.RegistrationRequest;
import com.heavengate.commons.RegistrationResponse;
import com.heavengate.commons.UnblockAccountRequest;
import com.heavengate.commons.UnblockAccountResponse;
import com.heavengate.commons.transactions.GetAccountHolderInfoRequest;
import com.heavengate.commons.transactions.GetAccountHolderInfoResponse;
import com.heavengate.domain.Result;

@Controller
public class ProvisioningController {
	
	@Autowired
	private ProvisioningService provisioningService;
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, value="registration")
	public ResponseEntity<Result<RegistrationResponse>> registration(RequestEntity<RegistrationRequest> request) {
		return this.provisioningService.registration(request);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, value="activation")
	public ResponseEntity<Result<ActivationResponse>> activation(RequestEntity<ActivationRequest> request) {
		return this.provisioningService.activation(request);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, value="blockaccount")
	public ResponseEntity<Result<BlockAccountResponse>> blockAccount(RequestEntity<BlockAccountRequest> request) {
		return this.provisioningService.blockAccount(request);
	}
	
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, value="unblockaccount")
	public ResponseEntity<Result<UnblockAccountResponse>> unblockAccount(RequestEntity<UnblockAccountRequest> request) {
		return this.provisioningService.unblockAccount(request);
	}
	
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, value="getaccountholderinfo")
	public ResponseEntity<Result<GetAccountHolderInfoResponse>> getAccountholderInfo(RequestEntity<GetAccountHolderInfoRequest> request) {
		return this.provisioningService.getAccountholderInfo(request);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, value="getuseraccounts")
	public ResponseEntity<Result<GetAccountsResponse>> getAccounts(RequestEntity<GetAccountsRequest> request) {
		return this.provisioningService.getAccounts(request);
	}
	
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, value="closeaccount")
	public ResponseEntity<Result<CloseAccountResponse>> closeAccount(RequestEntity<CloseAccountRequest> request) {
		return this.provisioningService.closeAccount(request);
	}

}
