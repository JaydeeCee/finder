package com.heavengate.authorization;


import java.util.Calendar;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import com.heavengate.commons.Errorcodes;
import com.heavengate.commons.ResetPasswordRequest;
import com.heavengate.commons.ResetPasswordResponse;
import com.heavengate.commons.authorization.LoginRequest;
import com.heavengate.commons.authorization.LoginResponse;
import com.heavengate.commons.authorization.LogoutRequest;
import com.heavengate.commons.authorization.LogoutResponse;
import com.heavengate.domain.Result;
import com.heavengate.utils.DateFormatter;
import com.heavengate.utils.Json;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class AuthorizationServiceBean implements AuthorizationService {
	
	@Value("${accessSecret}")
	private String accessSecret;
	
	@Value("${expiration.days}")
	private String expirydays;
	
	@Autowired
	private AuthorizationRepository authorizationRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	
	private final static Logger logger = LoggerFactory.getLogger(AuthorizationService.class);
	
	
	
	// login method
	@Transactional
	@Override
	public ResponseEntity<Result<LoginResponse>> login(RequestEntity<LoginRequest> request) {
		Result<LoginResponse> result = new Result<>();
		
		
		
		try {
			logger.debug(Json.toJson(request));
			
			LoginRequest loginRequest = request.getBody();

			
			if(loginRequest.getEmailaddress() != null || loginRequest.getMobilenumber() != null || loginRequest.getPassword() != null) {
				AuthorizationEntity authorizationEntity = null;
				authorizationEntity = authorizationRepository.findByEmailaddress(loginRequest.getEmailaddress());
				if(authorizationEntity == null) {
					authorizationEntity = authorizationRepository.findByMobilenumber(loginRequest.getMobilenumber());
					if(authorizationEntity == null) {
						authorizationEntity = authorizationRepository.findByUsername(loginRequest.getUsername());
					}
				}
				
				if(authorizationEntity != null) {
					
					// validate password
					if(this.passwordEncoder.matches(loginRequest.getPassword(), authorizationEntity.getPassword())) {
						
						//create a session with Jwt
						JwtBuilder builder = Jwts.builder()
								.claim("authorizationid", authorizationEntity.getAuthorizationid())
								.claim("mobilenumber", authorizationEntity.getMobilenumber())
								.claim("username", authorizationEntity.getUsername())
								.claim("emailaddress", authorizationEntity.getEmailaddress());
						
						builder.setExpiration(LocalDate.now()
								.plusDays(Integer.parseInt(expirydays))
								.toDate());
						
						builder.signWith(SignatureAlgorithm.HS512, accessSecret);
								
						authorizationEntity.setSessiontoken(builder.compact());
						authorizationEntity.setLastlogindate(Calendar.getInstance().getTime());
						authorizationRepository.save(authorizationEntity);
						
						LoginResponse loginResponse = new LoginResponse();
						loginResponse.setSessionToken(builder.compact());
						
						result.setStatus(true);
						result.setData(loginResponse);
						result.setMessage("User login successful");
					} else {
						result.setStatus(false);
						result.setErrorcode(Errorcodes.PASSWORD_MISMATCH);
					}
				} else {
					result.setStatus(false);
					result.setErrorcode(Errorcodes.AUTHORIZATION_NOT_FOUND);
				}
				
			} else {
				result.setStatus(false);
				result.setErrorcode(Errorcodes.INCOMPLETE_REQUEST_PARAMETERS);
			}
			
			
			
		} catch(RestClientException ex) {
			result.setStatus(false);
			result.setErrorcode(Errorcodes.INTERNAL_SERVER_ERROR);
			ex.printStackTrace(System.err);
		}
		return ResponseEntity.ok(result);
	}
	
	
	// logout
	@Override
	public ResponseEntity<Result<LogoutResponse>> logout(RequestEntity<LogoutRequest> request) {
		Result<LogoutResponse> result = new Result<>();
		
		try {
			logger.debug(Json.toJson(request));
			
			AuthorizationEntity authorizationEntity = this.validate(request.getHeaders());
			
			authorizationEntity.setSessiontoken(null);
			authorizationEntity.setLastlogoutdate(DateFormatter.getDate());
			authorizationRepository.save(authorizationEntity);
			
			LogoutResponse logoutResponse = new LogoutResponse();
			logoutResponse.setStatus(Boolean.TRUE);
			result.setData(logoutResponse);
			result.setStatus(true);
			result.setMessage("Logout successful");
			
		}catch(RestClientException ex) {
			result.setStatus(false);
			result.setMessage("Error logging out");
			ex.printStackTrace(System.err);
			
		}
		return ResponseEntity.ok(result);
	}
	
	
	
	// reset Password 
	@Override
	public ResponseEntity<Result<ResetPasswordResponse>> resetpassword(RequestEntity<ResetPasswordRequest> request) {
		
		Result<ResetPasswordResponse> result = new Result<>();
		
		try {
			logger.debug(Json.toJson(request));
			ResetPasswordRequest resetPasswordRequest = request.getBody();
			
			AuthorizationEntity requestingAuthorizationEntity  = null;
			
			
			if(resetPasswordRequest.getEmailaddress() != null || resetPasswordRequest.getMobilenumber() != null || resetPasswordRequest.getUsername() != null) {
				requestingAuthorizationEntity = this.validate(request.getHeaders());
				if(resetPasswordRequest.getPassword() != null) {
					AuthorizationEntity authorizationEntity = null;
					authorizationEntity = this.authorizationRepository.findByEmailaddress(resetPasswordRequest.getEmailaddress());
					if(authorizationEntity == null) {
						authorizationEntity = this.authorizationRepository.findByMobilenumber(resetPasswordRequest.getMobilenumber());
						if(authorizationEntity == null) {
							authorizationEntity = this.authorizationRepository.findByUsername(resetPasswordRequest.getUsername());
						}
					}
					
					// confirm that requestAuth is same as dbAuth
					if(authorizationEntity != null && requestingAuthorizationEntity != null ) {
						if(authorizationEntity.getAuthorizationid().equals(requestingAuthorizationEntity.getAuthorizationid()) && authorizationEntity.getSessiontoken() != null) {
							logger.debug(resetPasswordRequest.getNewpassword());
							logger.debug(resetPasswordRequest.getConfirmpassword());
							if(resetPasswordRequest.getNewpassword().equals(resetPasswordRequest.getConfirmpassword())) {
								if(this.passwordEncoder.matches(resetPasswordRequest.getPassword(), authorizationEntity.getPassword())) {
									authorizationEntity.setPassword(this.passwordEncoder.encode(resetPasswordRequest.getConfirmpassword()));
									authorizationEntity.setLastupdatedate(DateFormatter.getDate());
									
									authorizationRepository.save(authorizationEntity);
									
									ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
									resetPasswordResponse.setStatus(Boolean.TRUE);
									
									result.setData(resetPasswordResponse);
									result.setStatus(true);
									result.setMessage("Password Reset Successful");
									
								} else {
									result.setErrorcode(Errorcodes.INVALID_USER_PASSWORD);
									result.setStatus(false);
								}
							} else { 
								result.setErrorcode(Errorcodes.PASSWORD_MISMATCH);
								result.setStatus(false);
							}
							
						} else {
							result.setErrorcode(Errorcodes.OPERATION_FORBIDDEN);
							result.setErrorDescription("User not logged in or does not exist");
							result.setStatus(false);
							
						}
					} else {
						result.setErrorcode(Errorcodes.AUTHORIZATION_NOT_FOUND);
						result.setStatus(false);
					}
					
				} else {
					result.setErrorcode(Errorcodes.INCOMPLETE_REQUEST_PARAMETERS);
					result.setStatus(false);
				}
			} else {
				result.setErrorcode(Errorcodes.INCOMPLETE_REQUEST_PARAMETERS);
				result.setStatus(false);
			}
			
			
		} catch(RestClientException ex) {
				result.setStatus(false);
				result.setErrorcode(Errorcodes.INTERNAL_SERVER_ERROR);
			
		}
		
		return ResponseEntity.ok(result);
		
	};
	
	
	
	
	
	
	@Override
	public AuthorizationEntity validate(HttpHeaders httpHeader) {
		logger.debug("Inside Validate header method");
		logger.debug(Json.toJson(httpHeader));
		
		AuthorizationEntity authorizationEntity = null;
		try {
			
			if(httpHeader.containsKey("Authorization")) {
				Claims claims = Jwts.parser().setSigningKey(accessSecret).parseClaimsJws(httpHeader.get("Authorization").get(0)).getBody();
				logger.debug(Json.toJson(claims));
				authorizationEntity = this.authorizationRepository.findByAuthorizationid(claims.get("authorizationid", String.class));
				return authorizationEntity;
			}
			
		} catch(ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException| NullPointerException ex) {
			ex.printStackTrace(System.err);
		}
		return authorizationEntity;
	}

}
