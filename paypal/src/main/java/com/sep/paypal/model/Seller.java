package com.sep.paypal.model;

public class Seller {

	private String paypalClientId;
	private String paypalSecret;
	
	public Seller() {
		super();
	}
	
	public String getPaypalClientId() {
		return paypalClientId;
	}
	public void setPaypalClientId(String paypalClientId) {
		this.paypalClientId = paypalClientId;
	}
	public String getPaypalSecret() {
		return paypalSecret;
	}
	public void setPaypalSecret(String paypalSecret) {
		this.paypalSecret = paypalSecret;
	}
}
