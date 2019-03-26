package com.heavengate.commons.transactions;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankAccount implements Serializable {

	/**
	 * @author JayDee
	 */
	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("account_number")
	private String accountnumber;
	
	@JsonProperty("bank_code")
	private String bankcode;
	
	@JsonProperty("bank_name")
	private String bankname;
	
	@JsonProperty("account_name")
	private String accountname;

	
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

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	
	
	
	

}
