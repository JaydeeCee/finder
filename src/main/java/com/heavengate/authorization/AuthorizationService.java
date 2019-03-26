package com.heavengate.authorization;

import org.springframework.http.HttpHeaders;
import org.springframework.http.*;

import com.heavengate.commons.ResetPasswordRequest;
import com.heavengate.commons.ResetPasswordResponse;
import com.heavengate.commons.authorization.LoginRequest;
import com.heavengate.commons.authorization.LoginResponse;
import com.heavengate.commons.authorization.LogoutRequest;
import com.heavengate.commons.authorization.LogoutResponse;
import com.heavengate.domain.*;

public interface AuthorizationService {
	
	ResponseEntity<Result<LoginResponse>> login(RequestEntity<LoginRequest> request);

    ResponseEntity<Result<LogoutResponse>> logout(RequestEntity<LogoutRequest> request);
        
    ResponseEntity<Result<ResetPasswordResponse>> resetpassword(RequestEntity<ResetPasswordRequest> request);

	AuthorizationEntity validate(HttpHeaders httpheader);

}
