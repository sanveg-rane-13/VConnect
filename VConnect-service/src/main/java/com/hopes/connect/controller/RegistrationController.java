package com.hopes.connect.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hopes.connect.model.Client;
import com.hopes.connect.model.ClientService;
import com.hopes.connect.model.Service;
import com.hopes.connect.service.register.RegistrationService;
import com.hopes.connect.service.utility.BaseUtilityService;

/**
 * @author SaNNy - Sep 8, 2018
 */

@RestController
@RequestMapping("/connect/register")
public class RegistrationController {

	@Autowired
	private RegistrationService clientRegistrationService;

	@Autowired
	private RegistrationService serviceRegistrationService;
	
	@Autowired
	private RegistrationService clientServiceRegistrationService;

	@Autowired
	private BaseUtilityService baseUtilityService;

	private static Logger LOGGER = Logger.getLogger(RegistrationController.class);

	@GetMapping("/testRegistration")
	public String testReg(HttpServletRequest request) {
		String message = "Registration controller is running";
		LOGGER.info(message);
		return message;
	}

	@RequestMapping(value = "/client", method = RequestMethod.POST)
	public void registerClient(@RequestBody @Valid Client client) {
		long startTime = System.currentTimeMillis();

		clientRegistrationService.registerEntity(client);

		long endTime = System.currentTimeMillis();
		LOGGER.info("Time taken to register client: " + (endTime - startTime) + " millis");
	}

	@RequestMapping(value = "/checkClientReg", method = RequestMethod.POST)
	public boolean checkUniqueClientRegistrationId(@RequestBody String registrationId) {
		return baseUtilityService.isClientRegIdUnique(registrationId);
	}

	@RequestMapping(value = "/service", method = RequestMethod.POST)
	public void registerService(@RequestBody @Valid Service service) {
		long startTime = System.currentTimeMillis();

		serviceRegistrationService.registerEntity(service);

		long endTime = System.currentTimeMillis();
		LOGGER.info("Time taken to register new business service: " + (endTime - startTime) + " millis");
	}

	@RequestMapping(value = "/checkServiceName", method = RequestMethod.POST)
	public boolean checkUniqueServiceName(@RequestBody String serviceName) {
		return baseUtilityService.isServiceUnique(serviceName);
	}

	@RequestMapping(value = "/clientService", method = RequestMethod.POST)
	public void registerClientToService(@RequestBody ClientService clientService) {
		long startTime = System.currentTimeMillis();
		
		clientServiceRegistrationService.registerEntity(clientService);
		
		long endTime = System.currentTimeMillis();
		LOGGER.info("Time taken to register a client to a service: " + (endTime - startTime) + " millis");
	}
}
