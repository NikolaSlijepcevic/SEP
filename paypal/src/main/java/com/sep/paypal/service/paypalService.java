package com.sep.paypal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class paypalService {

	private static String CLIENT_ID = "AZnY9V6kHnwnTtPEe5Rr-nvmeHL7w5vwOeFP_cQwa9RacKlMv9hNAS-9vjmiFBQXi6FNABZls-XS9XnZ";
	private static String CLIENT_SECRET = "ENcQoSja-RxiA2AEAgXm67NY-lqIOR6JiAr-sWL_c3KUqFdQGcL1-MmsIrxCdrHiUw-ESVVocTL2KMmX";

	@Value("${paypal.mode}")
	private String mode;
	
	public Payment createPayment( PaymentRequest pr, String cancelUrl, String successUrl) throws PayPalRESTException{
		
		Amount amount = new Amount();
		amount.setCurrency("USD");
		double total = new BigDecimal(pr.getPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue();
		amount.setTotal(String.format("%.2f", total));

		Transaction transaction = new Transaction();
		transaction.setDescription("");
		transaction.setAmount(amount);

		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);

		Payer payer = new Payer();
		payer.setPaymentMethod("PAYPAL");
		
		Payment payment = new Payment();
		payment.setIntent("SALE");
		payment.setPayer(payer);  
		payment.setTransactions(transactions);
				
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(cancelUrl);
		redirectUrls.setReturnUrl(successUrl);
		payment.setRedirectUrls(redirectUrls);

		return payment.create(getApiContext(CLIENT_ID, CLIENT_SECRET));
	}
	
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
		Payment payment = new Payment();
		payment.setId(paymentId);
		PaymentExecution paymentExecute = new PaymentExecution();
		paymentExecute.setPayerId(payerId);
		return payment.execute(getApiContext(CLIENT_ID, CLIENT_SECRET), paymentExecute);
	}

	
	private APIContext getApiContext(String clientId, String clientSecret) throws PayPalRESTException {
		
		Map<String, String> configMap = new HashMap<>();
		configMap.put("mode", mode);
		
		APIContext context = new APIContext(new OAuthTokenCredential(clientId, clientSecret, configMap).getAccessToken());
		//APIContext context = new APIContext("A21AAG_H4i6oryT-qpwYF_j2bXFbbnrWdnZa7kLAvKOAc0Pl9Wm06elxR9VVhW_D4HfCcie0JdRLTvRO4puKEM3B2cGmVI1Bg");
		context.setConfigurationMap(configMap);
		return context;
	}
}
