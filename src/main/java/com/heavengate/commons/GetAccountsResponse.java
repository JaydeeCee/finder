package com.heavengate.commons;

import java.io.Serializable;
import java.util.List;

import com.heavengate.commons.transactions.Accounts;

public class GetAccountsResponse implements Serializable {

	/**
	 * @author JayDee
	 */
	private static final long serialVersionUID = 1L;

	private List<Accounts> accounts;

	public List<Accounts> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Accounts> accounts) {
		this.accounts = accounts;
	}

	
	
	
	

	
	
}
