package com.sep.paypal.model;

public class PaymentOrder {
	
	private String paymentId;
	private Seller seller;
	private String payerId;
	private double price;
	private String currency;
	private String method;
	private String intent;
	private String description;
	private PaymentOrderStatus status = PaymentOrderStatus.CREATED;
	
	public PaymentOrder() {
		super();
	}
	
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public String getPayerId() {
		return payerId;
	}
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public PaymentOrderStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentOrderStatus status) {
		this.status = status;
	}
	
	
}
