package com.heavengate.commons;

import java.io.Serializable;

public class ActivationRequest implements Serializable {
	
	/**
	 * @author JayDee
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String activationToken;
	private String password, confirmPassword;
	
	
	public String getActivationToken() {
		return activationToken;
	}
	
	
	public void setActivationToken(String activationToken) {
		this.activationToken = activationToken;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

}
