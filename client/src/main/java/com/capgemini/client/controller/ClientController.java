package com.capgemini.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RefreshScope
@RestController
public class ClientController {

	@Autowired
    private EurekaClient eurekaClient;
	
	private static final RestTemplate REST_TEMPLATE = new RestTemplate();
	
	@GetMapping("/message")
	public String getMessage() {
		Application application = eurekaClient.getApplication("config-client");
	       InstanceInfo instanceInfo = application.getInstances().get(0);
	       String url = "http://"+instanceInfo.getIPAddr()+ ":"+instanceInfo.getPort()+"/"+"message";
		return REST_TEMPLATE.getForObject(url, String.class);
	}
}
