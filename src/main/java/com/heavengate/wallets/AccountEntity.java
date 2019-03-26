package com.heavengate.wallets;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.heavengate.commons.AccountStatus;
import com.heavengate.commons.AccountType;

@Entity
@Table(schema="_account", name="_account")
public class AccountEntity implements Serializable {

	/**
	 * @author JayDee 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable= false, unique = true)
	private String accountid;
	
	private String accountname;
	
	private String bankname;
	
	private String bankcode;
	
	private String accountnumber;
	
	@Column(columnDefinition = "TEXT")
	private String accountdescription;
	
	@Column(precision = 23, scale= 2)
	private BigDecimal availablebalance, bookbalance, committedbalance;
	
	
	private String currency;
	
	@Column(nullable=false)
	private AccountType accounttype;
	
	@Column(nullable = false)
	private AccountStatus accountstatus;
	private String authorizationid;
	
	@Column(unique = true)
	private String last4figures;
	
	private String cardtype, expirymonth, expiryyear;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationdate, lastupdateddate;
	
	

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getAccountdescription() {
		return accountdescription;
	}

	public void setAccountdescription(String accountdescription) {
		this.accountdescription = accountdescription;
	}

	public BigDecimal getAvailablebalance() {
		return availablebalance;
	}

	public void setAvailablebalance(BigDecimal availablebalance) {
		this.availablebalance = availablebalance;
	}

	public BigDecimal getBookbalance() {
		return bookbalance;
	}

	public void setBookbalance(BigDecimal bookbalance) {
		this.bookbalance = bookbalance;
	}

	public BigDecimal getCommittedbalance() {
		return committedbalance;
	}

	public void setCommittedbalance(BigDecimal committedbalance) {
		this.committedbalance = committedbalance;
	}

	public String getCurrency() {
		return currency;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public AccountType getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(AccountType accounttype) {
		this.accounttype = accounttype;
	}

	public AccountStatus getAccountstatus() {
		return accountstatus;
	}

	public void setAccountstatus(AccountStatus accountstatus) {
		this.accountstatus = accountstatus;
	}

	public String getAuthorizationid() {
		return authorizationid;
	}

	public void setAuthorizationid(String authorizationid) {
		this.authorizationid = authorizationid;
	}

	public String getLast4figures() {
		return last4figures;
	}

	public void setLast4figures(String last4figures) {
		this.last4figures = last4figures;
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getExpirymonth() {
		return expirymonth;
	}

	public void setExpirymonth(String expirymonth) {
		this.expirymonth = expirymonth;
	}

	public String getExpiryyear() {
		return expiryyear;
	}

	public void setExpiryyear(String expiryyear) {
		this.expiryyear = expiryyear;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Date getLastupdateddate() {
		return lastupdateddate;
	}

	public void setLastupdateddate(Date lastupdateddate) {
		this.lastupdateddate = lastupdateddate;
	}
}
