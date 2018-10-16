package com.capgemini.service2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
public class Service2Controller {

	private static final RestTemplate REST_TEMPLATE = new RestTemplate();
	private static final String baseUrl = "http://client/";
	
	@Value("${message:Hello service2controller}")
	private String message;

	@RequestMapping("/message")
	String getMessage() {
		return this.message;
	}
}
