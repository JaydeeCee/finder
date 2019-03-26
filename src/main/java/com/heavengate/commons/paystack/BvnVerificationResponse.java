package com.heavengate.commons.paystack;

import java.io.Serializable;

public class BvnVerificationResponse implements Serializable{
	
	
	/**
	 * @author JayDee
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String first_name;
	private String last_name;
	private String dob;
	private String mobile;
	private String bvn;
	
	
	
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBvn() {
		return bvn;
	}
	public void setBvn(String bvn) {
		this.bvn = bvn;
	}
	
	

}
