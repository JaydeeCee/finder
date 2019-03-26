package com.heavengate.commons.transactions;

import java.io.Serializable;
import java.util.Date;

public class GetAccountHolderInfoResponse implements Serializable{

	/**
	 * @author JayDee
	 */
	private static final long serialVersionUID = 1L;
	
	private String firstname,middlename,lastname;
    private String authorizationid,accountholderid;
    private Integer birthyear,birthmonth; 
    private String mobilenumber,emailaddress;
    private String address;
    private Date registrationdate;
    
    
    
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
	public String getAuthorizationid() {
		return authorizationid;
	}
	public void setAuthorizationid(String authorizationid) {
		this.authorizationid = authorizationid;
	}
	public String getAccountholderid() {
		return accountholderid;
	}
	public void setAccountholderid(String accountholderid) {
		this.accountholderid = accountholderid;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRegistrationdate() {
		return registrationdate;
	}
	public void setRegistrationdate(Date registrationdate) {
		this.registrationdate = registrationdate;
	}
    


}
