package com.rest.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Orders {
	@Id@GeneratedValue
	private int transactionId;
	private java.util.Date dateOfDelivery;
	private int consumerId;
	private int farmId;
	private int addressId;
	private String status;
	private int quantityBooked;
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public java.util.Date getDateOfDelivery() {
		return dateOfDelivery;
	}
	public void setDateOfDelivery(java.util.Date dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}
	public int getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(int consumerId) {
		this.consumerId = consumerId;
	}
	public int getFarmId() {
		return farmId;
	}
	public void setFarmId(int farmId) {
		this.farmId = farmId;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getQuantityBooked() {
		return quantityBooked;
	}
	public void setQuantityBooked(int quantityBooked) {
		this.quantityBooked = quantityBooked;
	}
	public Orders(int transactionId, Date dateOfDelivery, int consumerId, int farmId, int addressId, String status,
			int quantityBooked) {
		super();
		this.transactionId = transactionId;
		this.dateOfDelivery = dateOfDelivery;
		this.consumerId = consumerId;
		this.farmId = farmId;
		this.addressId = addressId;
		this.status = status;
		this.quantityBooked = quantityBooked;
	}
	@Override
	public String toString() {
		return "Orders [transactionId=" + transactionId + ", dateOfDelivery=" + dateOfDelivery + ", consumerId="
				+ consumerId + ", farmId=" + farmId + ", addressId=" + addressId + ", status=" + status
				+ ", quantityBooked=" + quantityBooked + "]";
	}
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

