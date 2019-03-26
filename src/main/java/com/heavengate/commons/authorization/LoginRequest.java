package com.heavengate.commons.authorization;

import java.io.Serializable;

public class LoginRequest implements Serializable {
	
	/**
	 * @author JayDee
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String username, mobilenumber, emailaddress;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
