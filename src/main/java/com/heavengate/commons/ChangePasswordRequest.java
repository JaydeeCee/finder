package com.heavengate.commons;

import java.io.Serializable;

public class ChangePasswordRequest implements Serializable{

	/**
	 *  @author JayDee
	 */
	private static final long serialVersionUID = 1L;
	
	private String oldpassword, newpassword, confirmpassword;

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	
	

}
