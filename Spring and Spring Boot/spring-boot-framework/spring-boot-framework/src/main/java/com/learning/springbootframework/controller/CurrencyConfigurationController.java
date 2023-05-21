package com.learning.springbootframework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springbootframework.bean.CurrencyConfiguration;

@RestController
public class CurrencyConfigurationController {

	@Autowired
	private CurrencyConfiguration configuration;

	@RequestMapping("/currencyConfig")
	public CurrencyConfiguration returnConfig() {
		return configuration;
	}
}
