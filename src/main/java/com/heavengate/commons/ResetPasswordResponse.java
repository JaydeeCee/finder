package com.heavengate.commons;

import java.io.Serializable;

public class ResetPasswordResponse implements Serializable {
	
	/**
	 * @author JayDee
	 */
	
	
	private static final long serialVersionUID = 1L;
	
	
	private Boolean status;
	

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	

}
