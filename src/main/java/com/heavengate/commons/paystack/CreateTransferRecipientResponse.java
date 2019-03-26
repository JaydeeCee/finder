package com.heavengate.commons.paystack;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.heavengate.commons.transactions.BankAccount;

public class CreateTransferRecipientResponse implements Serializable {

	/**
	 * @author JayDee
	 */
	private static final long serialVersionUID = 1L;
	
	private String type, name, description;
	
	private BankAccount account;
	
	private String currency;
	
	@JsonProperty("recipient_code")
	private String recipientcode;
	
	private Boolean active;
	
	private int id;
	
	private String createdAt, updatedAt;
	
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BankAccount getAccount() {
		return account;
	}

	public void setAccount(BankAccount account) {
		this.account = account;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRecipientcode() {
		return recipientcode;
	}

	public void setRecipientcode(String recipientcode) {
		this.recipientcode = recipientcode;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}	
}
