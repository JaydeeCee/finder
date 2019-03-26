package com.heavengate.commons.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankAccountVerificationResponse {
	
	
	@JsonProperty("account_number")
	private String accountnumber;
	
	@JsonProperty("account_name")
	private String accountname;

	
	
	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	
	
	

}
