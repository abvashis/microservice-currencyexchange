package com.microservice.currencyexchange;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment env;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
		//ExchangeValue response = new ExchangeValue(1L, "USD", "INR", BigDecimal.valueOf(65)); 
		ExchangeValue response = repository.findByFromAndTo(from, to);
		response.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		logger.info("response --> {}", response);
		return response;
	}

}
