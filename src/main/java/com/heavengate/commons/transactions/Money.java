package com.heavengate.commons.transactions;

import java.io.Serializable;
import java.math.BigDecimal;

public class Money implements Serializable {
	
	/**
	 * @author JayDee
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal amount;
	private String currency;
	
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
	

}
