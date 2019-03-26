package com.heavengate.commons;

import java.io.Serializable;

public class ActivationResponse implements Serializable {
	
	/**
	 * @author JayDee
	 */
	private static final long serialVersionUID = 1L;
	
	
	private boolean activationStatus;

	public boolean getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

}
