package com.heavengate.commons.paystack;

import java.io.Serializable;

public class BvnVerificationRequest implements Serializable {
	
	
	/**
	 * @author JayDee
	 */
	private static final long serialVersionUID = 1L;
	private String bvn;

	public String getBvn() {
		return bvn;
	}

	public void setBvn(String bvn) {
		this.bvn = bvn;
	}
	
	
	

}
