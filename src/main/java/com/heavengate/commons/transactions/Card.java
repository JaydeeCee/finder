package com.heavengate.commons.transactions;

import java.io.Serializable;

public class Card implements Serializable{
	
	 /**
	 * @author JayDee
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private String cardtype,last4,expirymonth,expiryyear,token;

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getLast4() {
		return last4;
	}

	public void setLast4(String last4) {
		this.last4 = last4;
	}

	public String getExpirymonth() {
		return expirymonth;
	}

	public void setExpirymonth(String expirymonth) {
		this.expirymonth = expirymonth;
	}

	public String getExpiryyear() {
		return expiryyear;
	}

	public void setExpiryyear(String expiryyear) {
		this.expiryyear = expiryyear;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	} 
	 
	 

}
