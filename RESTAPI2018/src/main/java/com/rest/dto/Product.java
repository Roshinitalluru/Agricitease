package com.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "productId")
@XmlRootElement
@Entity
public class Product {
	@Id@GeneratedValue
	private int productId;
	private String productName;
	
	private String imageName;
	private String videoName;
	
	@OneToOne(mappedBy="product",fetch = FetchType.LAZY)
	//@Fetch(value = FetchMode.SUBSELECT)
	private Farm farm ;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}

	public Product(int productId, String productName, String imageName, String videoName, Farm farm) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.imageName = imageName;
		this.videoName = videoName;
		this.farm = farm;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", imageName=" + imageName
				+ ", videoName=" + videoName + ", farm=" + farm + "]";
	}
	
	
}
