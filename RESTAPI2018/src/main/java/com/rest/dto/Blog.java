package com.rest.dto;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity

public class Blog {
	@Id@GeneratedValue
	private int blogId;
	private String name;
	private String role;
	private int rating;
	private String review;
	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Blog(int blogId, String name, String role, int rating, String review) {
		super();
		this.blogId = blogId;
		this.name = name;
		this.role = role;
		this.rating = rating;
		this.review = review;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", name=" + name + ", role=" + role + ", rating=" + rating + ", review="
				+ review + "]";
	}
	
}
