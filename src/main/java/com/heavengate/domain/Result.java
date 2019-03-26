package com.heavengate.domain;

import com.heavengate.commons.Errorcodes;

public class Result<T> {
	
	
	private boolean status;
	private String message;
	private T data;
	private Errorcodes errorcode;
	private String errorDescription;
	
	public void setData(T data) {
		this.data = data;
	}
	
	public T getData() {
		return this.data;
	}
	
	public void setStatus(boolean stat) {
		this.status = stat;
	}
	
	
	public boolean getStatus() {
		return this.status;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

	public Errorcodes getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(Errorcodes errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	

}
