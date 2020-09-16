package com.rest.dto;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
@Entity
public class Farm {
	@Id@GeneratedValue
	private int farmId;
	private java.util.Date doj;
	private String crop;
	private int cropQuantity;
	private int fieldArea;
	private String kindOfCrop;
	private double price;
	private int remainingQuantity;
	
	@ManyToOne
	@JoinColumn(name="addressId")
	private Address address;
	
	@OneToOne
	@JoinColumn(name="productId")
	private Product product;

	public int getFarmId() {
		return farmId;
	}

	public void setFarmId(int farmId) {
		this.farmId = farmId;
	}

	public java.util.Date getDoj() {
		return doj;
	}

	public void setDoj(java.util.Date doj) {
		this.doj = doj;
	}

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	public int getCropQuantity() {
		return cropQuantity;
	}

	public void setCropQuantity(int cropQuantity) {
		this.cropQuantity = cropQuantity;
	}

	public int getFieldArea() {
		return fieldArea;
	}

	public void setFieldArea(int fieldArea) {
		this.fieldArea = fieldArea;
	}

	public String getKindOfCrop() {
		return kindOfCrop;
	}

	public void setKindOfCrop(String kindOfCrop) {
		this.kindOfCrop = kindOfCrop;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Farm(int farmId, Date doj, String crop, int cropQuantity, int fieldArea, String kindOfCrop, double price,
			int remainingQuantity, Address address, Product product) {
		super();
		this.farmId = farmId;
		this.doj = doj;
		this.crop = crop;
		this.cropQuantity = cropQuantity;
		this.fieldArea = fieldArea;
		this.kindOfCrop = kindOfCrop;
		this.price = price;
		this.remainingQuantity = remainingQuantity;
		this.address = address;
		this.product = product;
	}

	public Farm() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Farm [farmId=" + farmId + ", doj=" + doj + ", crop=" + crop + ", cropQuantity=" + cropQuantity
				+ ", fieldArea=" + fieldArea + ", kindOfCrop=" + kindOfCrop + ", price=" + price
				+ ", remainingQuantity=" + remainingQuantity + ", address=" + address + ", product=" + product + "]";
	}

	
	}
