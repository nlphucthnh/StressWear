package com.web.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PaypalConfig {
    private String clientId = "AXmmx1__h1I2m_m0jMkEjLpd_N3qQ2Qnu6ycJFwS586JJKebpAEvG90HS4NpOF2Spyob8KvFYvs4YK6w";
	private String clientSecret = "EAAeH6RK8g92WHNl4EFlfD0TuBg2L6VJRxto0IGFW3-ZqN9C1m80dN776WB9yNfgtUdwmKVKuLXH9Cgi";
	private String mode = "sandbox";

	@Bean
	public Map<String, String> paypalSdkConfig() {
		Map<String, String> sdkConfig = new HashMap<>();
		sdkConfig.put("mode", mode);
		return sdkConfig;
	}

	@Bean
	public APIContext apiContext() throws PayPalRESTException {
		APIContext apiContext = new APIContext(clientId, clientSecret, mode);
		apiContext.setConfigurationMap(paypalSdkConfig());
		return apiContext;
	}
}
