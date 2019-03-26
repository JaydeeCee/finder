package com.heavengate.commons;

import java.io.Serializable;

public class UnblockAccountResponse implements Serializable {

	/**
	 * @author JayDee
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean status;
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
