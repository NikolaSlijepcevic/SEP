package com.sep.paypal.model;

public class Seller {

	private String paypalClientId;
	private String paypalSecret;
	
	public Seller() {
		super();
	}
	
	public Seller(String paypalClientId, String paypalSecret) {
		super();
		this.paypalClientId = paypalClientId;
		this.paypalSecret = paypalSecret;
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
