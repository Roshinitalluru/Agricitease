package com.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "userId")
@Entity
public class User {
	@Id@GeneratedValue
	private int userId;
	private String userName;
	private String password;
	private String fullName;
	private int age;
	private String qualification;
	private String mobileNumber;
	private String role;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Address> addressList = new ArrayList<Address>();
	
	/*@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Farm> farmsList = new ArrayList<Farm>();
	public List<Address> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
	public List<Farm> getFarmsList() {
		return farmsList;
	}
	public void setFarmsList(List<Farm> farmsList) {
		this.farmsList = farmsList;
	}*/
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", fullName=" + fullName
				+ ", age=" + age + ", qualification=" + qualification
				+ ", mobileNumber=" + mobileNumber + ", role=" + role + "]";
	}
	public User(int userId, String userName, String password, String fullName,
			int age, String qualification, String mobileNumber, String role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.age = age;
		this.qualification = qualification;
		this.mobileNumber = mobileNumber;
		this.role = role;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
