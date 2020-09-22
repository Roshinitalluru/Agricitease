package com.rest.dto;

public class Statistics {

	private String crop;
	private int counts;
	public String getCrop() {
		return crop;
	}
	public void setCrop(String crop) {
		this.crop = crop;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public Statistics() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Statistics(String crop, int counts) {
		super();
		this.crop = crop;
		this.counts = counts;
	}
	@Override
	public String toString() {
		return "Statistics [crop=" + crop + ", counts=" + counts + "]";
	}
	
}
