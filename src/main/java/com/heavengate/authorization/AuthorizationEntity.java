package com.heavengate.authorization;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.heavengate.commons.AuthorizationStatus;
import com.heavengate.commons.AuthorizationType;

import javax.persistence.*;

@Entity
@Table(schema= "_authorization_table", name="_authorization_name")
public class AuthorizationEntity implements Serializable {
	
	/**
	 *  @author JayDee
	 */
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false, unique=true)
	private String authorizationid;
	
	@Column(unique= true)
	private String emailaddress;
	
	@Column(unique = true)
	private String mobilenumber;
	
	@Column(unique = true)
	private String username;
	
	
	private String password;
	
	
	@Column(unique = true)
	private String resetpasswordtoken, activationtoken;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastlogindate, lastlogoutdate, activationdate;
	
	
	@Column(unique= true, columnDefinition = "TEXT")
	private String sessiontoken;
	
	
	private AuthorizationType authorizationtype;
	
	private AuthorizationStatus authorizationstatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationdate, lastupdatedate;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getAuthorizationid() {
		return authorizationid;
	}


	public void setAuthorizationid(String authorizationid) {
		this.authorizationid = authorizationid;
	}


	public String getEmailaddress() {
		return emailaddress;
	}


	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}


	public String getMobilenumber() {
		return mobilenumber;
	}


	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getResetpasswordtoken() {
		return resetpasswordtoken;
	}


	public void setResetpasswordtoken(String resetpasswordtoken) {
		this.resetpasswordtoken = resetpasswordtoken;
	}


	public String getActivationtoken() {
		return activationtoken;
	}


	public void setActivationtoken(String activationtoken) {
		this.activationtoken = activationtoken;
	}
	
	


	public Date getLastlogindate() {
		return lastlogindate;
	}


	public void setLastlogindate(Date lastlogindate) {
		this.lastlogindate = lastlogindate;
	}


	public Date getLastlogoutdate() {
		return lastlogoutdate;
	}


	public void setLastlogoutdate(Date lastlogoutdate) {
		this.lastlogoutdate = lastlogoutdate;
	}


	public Date getActivationdate() {
		return activationdate;
	}


	public void setActivationdate(Date activationdate) {
		this.activationdate = activationdate;
	}


	public String getSessiontoken() {
		return sessiontoken;
	}


	public void setSessiontoken(String sessiontoken) {
		this.sessiontoken = sessiontoken;
	}


	public AuthorizationType getAuthorizationtype() {
		return authorizationtype;
	}


	public void setAuthorizationtype(AuthorizationType authorizationtype) {
		this.authorizationtype = authorizationtype;
	}


	public Date getCreationdate() {
		return creationdate;
	}



	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	
	public AuthorizationStatus getAuthorizationstatus() {
		return authorizationstatus;
	}


	public void setAuthorizationstatus(AuthorizationStatus authorizationstatus) {
		this.authorizationstatus = authorizationstatus;
	}


	public Date getLastupdatedate() {
		return lastupdatedate;
	}


	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	
	
	
	

}
