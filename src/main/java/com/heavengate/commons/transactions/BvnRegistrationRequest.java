package com.heavengate.commons.transactions;

import java.io.Serializable;

public class BvnRegistrationRequest  implements Serializable{

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
