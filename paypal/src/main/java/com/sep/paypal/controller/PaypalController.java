package com.sep.paypal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class PaypalController {

	@Autowired
	PayPalService paypalService;
	
	@RequestMapping("/test")
	public String test(){
		return " paypal ";
	}
}
