package com.heavengate.provisioning;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(schema = "_accountholder", name = "_accountholder")
public class AccountHolderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, unique = true)
	private String authorizationid, accountholderid;
	
	private String firstname, middlename, lastname;
	
	private Integer birthyear, birthmonth;
	
	private String address;
	
	@Temporal(TemporalType.DATE)
	private Date registrationdate;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorizationid() {
		return authorizationid;
	}

	public void setAuthorizationid(String authorizationid) {
		this.authorizationid = authorizationid;
	}

	public String getAccountholderid() {
		return accountholderid;
	}

	public void setAccountholderid(String accountholderid) {
		this.accountholderid = accountholderid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(Integer birthyear) {
		this.birthyear = birthyear;
	}

	public Integer getBirthmonth() {
		return birthmonth;
	}

	public void setBirthmonth(Integer birthmonth) {
		this.birthmonth = birthmonth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegistrationdate() {
		return registrationdate;
	}

	public void setRegistrationdate(Date registrationdate) {
		this.registrationdate = registrationdate;
	}
	
	
	
}
