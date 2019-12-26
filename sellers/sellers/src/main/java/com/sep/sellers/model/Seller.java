package com.sep.sellers.model;

import java.util.List;

public class Seller {

	private long id;
	
	private String name;
	
	private List<PaymentType> paymentTypes;
	
	public Seller(long id, String name, List<PaymentType> paymentTypes) {
		super();
		this.id = id;
		this.name = name;
		this.paymentTypes = paymentTypes;
	}
	
	public Seller() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
}
