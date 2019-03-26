package com.heavengate.commons.transactions;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddAccountRequest implements Serializable {
	
	/**
	 * @author JayDee
	 */
	private static final long serialVersionUID = 1L;

	private String bankname;
	
	@JsonProperty("bank_code")
	private String bankcode;
	
	@JsonProperty("account_number")
	private String accountnumber;

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
}
