package com.hopes.connect.service.register;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.hopes.connect.model.Client;
import com.hopes.connect.model.ClientService;
import com.hopes.connect.model.MetaEntity;
import com.hopes.connect.model.Service;
import com.hopes.connect.model.exception.ErrorCode;
import com.hopes.connect.model.exception.RegistrationException;
import com.hopes.connect.repository.ClientRepository;
import com.hopes.connect.repository.ClientServiceRepository;
import com.hopes.connect.repository.ServiceRepository;
import com.hopes.connect.service.utility.BaseUtilityService;

/**
 * @author SaNNy - Sep 10, 2018
 */

@org.springframework.stereotype.Service("clientServiceRegistrationService")
public class ClientServiceRegistrationService implements RegistrationService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private ClientServiceRepository clientServiceRepository;

	@Autowired
	private BaseUtilityService baseUtilityService;

	@Override
	public boolean validateEntity(MetaEntity entity) {
		if (!(entity instanceof ClientService)) {
			String errorCode = ErrorCode.THREE.toString(); // error code if entity type miss match
			throw new RegistrationException(errorCode);
		}

		ClientService clientService = (ClientService) entity;

		Client client = clientService.getClient();
		Service service = clientService.getService();

		if (client == null || service == null) {
			String errorCode = ErrorCode.ONE.toString(); // error code for null value
			throw new RegistrationException(errorCode);
		}

		if (client.getClientId() == null || service.getServiceId() == null) {
			String errorCode = ErrorCode.ONE.toString(); // error code for null value
			throw new RegistrationException(errorCode);
		}

		return true;
	}

	private void validateRegistration(Client client, Service service) {

		// validating client and service before registering service to client
		if (client == null || service == null || client.getClientId() == null || service.getServiceId() == null) {
			LOGGER.error("Client or Service not registered");
			String errorCode = ErrorCode.FOUR.toString();
			throw new RegistrationException(errorCode);
		}

		// check if same relation exists
		if (!this.baseUtilityService.isClientServiceUnique(client.getClientId(), service.getServiceId())) {
			LOGGER.error("Client - Service relation already registered");
			String errorCode = ErrorCode.FIVE.toString();
			throw new RegistrationException(errorCode);
		}
	}

	@Override
	public void registerEntity(MetaEntity entity) {
		if (this.validateEntity(entity)) {

			ClientService clientServiceToRegister = (ClientService) entity;

			Client client = clientRepository.findByClientId(clientServiceToRegister.getClient().getClientId());
			Service service = serviceRepository.findByServiceId(clientServiceToRegister.getService().getServiceId());

			this.validateRegistration(client, service);

			clientServiceToRegister.setClient(client);
			clientServiceToRegister.setService(service);

			Date startDate = new Date();
			clientServiceToRegister.setSubStartDate(startDate);

			// TODO: Setup end date as per service from instead hard coded six months
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.add(Calendar.MONTH, +6);

			Date endDate = calendar.getTime();
			clientServiceToRegister.setSubEndDate(endDate);

			clientServiceRepository.save(clientServiceToRegister);
		}
	}

}
