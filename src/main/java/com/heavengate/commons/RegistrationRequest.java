package com.heavengate.commons;

import java.io.Serializable;

public class RegistrationRequest implements Serializable {
	
	/**
	 * @author JayDee
	 */
	private static final long serialVersionUID = 1L;

	private String firstname, middlename, lastname;
	
	private Integer birthyear, birthmonth;
	
	private String address;
	
	private String mobilenumber,emailaddress,username;
	
    private AuthorizationType authorizationtype;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(Integer birthyear) {
		this.birthyear = birthyear;
	}

	public Integer getBirthmonth() {
		return birthmonth;
	}

	public void setBirthmonth(Integer birthmonth) {
		this.birthmonth = birthmonth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public AuthorizationType getAuthorizationtype() {
		return authorizationtype;
	}

	public void setAuthorizationtype(AuthorizationType authorizationtype) {
		this.authorizationtype = authorizationtype;
	}

}
