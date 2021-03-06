package com.sep.paypal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.sep.paypal.model.PaymentRequest;
import com.sep.paypal.service.paypalService;
import com.sep.paypal.utils.TokenUtils;

@RequestMapping("/")
@RestController
public class PaypalController {

	@Autowired
	paypalService payPalService;
	
	@Autowired
	TokenUtils tokenUtils;

	@RequestMapping("/test")
	public String test(){
		return " paypal ";
	}
	
	@PostMapping("/createPayment")
	public String createPayment() {
		
		PaymentRequest pr = tokenUtils.getPaymentRequest();
		try {
			Payment payment = payPalService.createPayment(pr);
			for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					return link.getHref();
				}
			}			
		} catch (PayPalRESTException e) {
			e.printStackTrace();
		}
		return "https://localhost:8672/paypal/error.html";
	}
	
	/*@PostMapping("/cancel")
	public void cancelPay(@RequestBody long id) {
		 payPalService.canclePaymentOrder(id);
	}*/

	 @GetMapping("/success")
	 public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
	        try {
	            Payment payment = payPalService.executePayment(paymentId, payerId);
	            System.out.println(payment.toJSON());
	            if (payment.getState().equals("approved")) {
	                return "https://localhost:8672/paypal/success.html";
	            }
	        } catch (PayPalRESTException e) {
	         System.out.println(e.getMessage());
	        }
	        return "https://localhost:8672/paypal/error.html";
	    }

	 @PostMapping("/paymentOrderAmount")
	 public Double getPaymentOrderPrice(@RequestBody String paymentOrderId) {
		 return payPalService.getPaymentOrderPrice(paymentOrderId);
	 }
	
}
