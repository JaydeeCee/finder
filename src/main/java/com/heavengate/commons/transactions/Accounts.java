package com.heavengate.commons.transactions;

import java.io.Serializable;

import com.heavengate.commons.AccountStatus;
import com.heavengate.commons.AccountType;

public class Accounts implements Serializable {

	/**
	 * @author JayDee
	 */
	private static final long serialVersionUID = 1L;
	
	 private String accountidentity;
	    private String description;
	    private Money availablebalance;
	    private AccountStatus accountstatus;
	    private AccountType accounttype;
	    private Card card;
	    
	    
	    
		public String getAccountidentity() {
			return accountidentity;
		}
		public void setAccountidentity(String accountidentity) {
			this.accountidentity = accountidentity;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Money getAvailablebalance() {
			return availablebalance;
		}
		public void setAvailablebalance(Money availablebalance) {
			this.availablebalance = availablebalance;
		}
		public AccountStatus getAccountstatus() {
			return accountstatus;
		}
		public void setAccountstatus(AccountStatus accountstatus) {
			this.accountstatus = accountstatus;
		}
		public AccountType getAccounttype() {
			return accounttype;
		}
		public void setAccounttype(AccountType accounttype) {
			this.accounttype = accounttype;
		}
		public Card getCard() {
			return card;
		}
		public void setCard(Card card) {
			this.card = card;
		}

	    
	    
	    
}
