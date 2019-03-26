package com.heavengate.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.heavengate.commons.ResetPasswordRequest;
import com.heavengate.commons.ResetPasswordResponse;
import com.heavengate.commons.authorization.LoginRequest;
import com.heavengate.commons.authorization.LoginResponse;
import com.heavengate.commons.authorization.LogoutRequest;
import com.heavengate.commons.authorization.LogoutResponse;
import com.heavengate.domain.Result;

@Controller
public class AuthorizationController {
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, value="login")
	ResponseEntity<Result<LoginResponse>> login(RequestEntity<LoginRequest> request) {
		return this.authorizationService.login(request);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, value="logout")
	public ResponseEntity<Result<LogoutResponse>> logout(RequestEntity<LogoutRequest> request) {
		return this.authorizationService.logout(request);
	}
	
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, value="resetpassword")
	public ResponseEntity<Result<ResetPasswordResponse>> resetpassword(RequestEntity<ResetPasswordRequest> request) {
		return this.authorizationService.resetpassword(request);
	}

}
