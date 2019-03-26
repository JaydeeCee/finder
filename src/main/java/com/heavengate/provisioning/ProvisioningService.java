package com.heavengate.provisioning;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.heavengate.commons.ActivationRequest;
import com.heavengate.commons.ActivationResponse;
import com.heavengate.commons.BlockAccountRequest;
import com.heavengate.commons.BlockAccountResponse;
import com.heavengate.commons.ChangePasswordRequest;
import com.heavengate.commons.ChangePasswordResponse;
import com.heavengate.commons.CloseAccountRequest;
import com.heavengate.commons.CloseAccountResponse;
import com.heavengate.commons.GetAccountsRequest;
import com.heavengate.commons.GetAccountsResponse;
import com.heavengate.commons.RegistrationRequest;
import com.heavengate.commons.RegistrationResponse;
import com.heavengate.commons.ResetPasswordRequest;
import com.heavengate.commons.ResetPasswordResponse;
import com.heavengate.commons.UnblockAccountRequest;
import com.heavengate.commons.UnblockAccountResponse;
import com.heavengate.commons.transactions.GetAccountHolderInfoRequest;
import com.heavengate.commons.transactions.GetAccountHolderInfoResponse;
import com.heavengate.domain.Result;

public interface ProvisioningService {
	
	ResponseEntity<Result<RegistrationResponse>> registration(RequestEntity<RegistrationRequest> request);
	
	ResponseEntity<Result<ActivationResponse>> activation(RequestEntity<ActivationRequest> request);
	
	ResponseEntity<Result<BlockAccountResponse>> blockAccount(RequestEntity<BlockAccountRequest> request);

	//ResponseEntity<Result<ChangePasswordResponse>> changePassword(RequestEntity<ChangePasswordRequest> request);

    ResponseEntity<Result<CloseAccountResponse>> closeAccount(RequestEntity<CloseAccountRequest> request);

    ResponseEntity<Result<GetAccountsResponse>> getAccounts(RequestEntity<GetAccountsRequest> request);

  //  ResponseEntity<Result<ResetPasswordResponse>> resetPassword(RequestEntity<ResetPasswordRequest> request);

    ResponseEntity<Result<UnblockAccountResponse>> unblockAccount(RequestEntity<UnblockAccountRequest> request);

    ResponseEntity<Result<GetAccountHolderInfoResponse>> getAccountholderInfo(RequestEntity<GetAccountHolderInfoRequest> request);
}
