package com.web.Controller.User_Controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.web.config.PaypalPaymentIntent;
import com.web.config.PaypalPaymentMethod;
import com.web.service.PaypalService;
import com.web.utils.Utils;


@Controller
public class PaymentController {
    
    
	// tk: sb-es5f4727003177@personal.example.com
	//mk: DV/#c7$5

    public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";

	private Logger log = LoggerFactory.getLogger(getClass());
@Autowired
	private PaypalService paypalService;


	@PostMapping("/pay")
	public String pay(HttpServletRequest request, @RequestParam("price") double price) {
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(price, "USD", PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale, "payment description", cancelUrl, successUrl);
			for (Links links : payment.getLinks())
				if (links.getRel().equals("approval_url"))
					return "redirect:" + links.getHref();
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay() {
		return "/message/error";
	}	

	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay(Model model,@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
		try {
		
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved"))
				return "/message/success";
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "/message/success";
	}
	
	// profile
}
