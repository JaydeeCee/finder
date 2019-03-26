package com.heavengate.commons.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankAccountVerificationRequest {
	
	
	@JsonProperty("account_number")
	private String accountnumber;
	
	@JsonProperty("bank_code")
	private String bankcode;

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	
	
	

}
