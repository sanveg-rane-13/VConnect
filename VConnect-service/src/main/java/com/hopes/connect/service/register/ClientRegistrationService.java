package com.hopes.connect.service.register;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopes.connect.model.Client;
import com.hopes.connect.model.MetaEntity;
import com.hopes.connect.model.exception.ErrorCode;
import com.hopes.connect.model.exception.RegistrationException;
import com.hopes.connect.repository.ClientRepository;
import com.hopes.connect.service.utility.BaseUtilityService;

/**
 * @author SaNNy - Sep 8, 2018
 */

@Service("clientRegistrationService")
public class ClientRegistrationService implements RegistrationService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BaseUtilityService baseUtilityService;

	private static Logger LOGGER = Logger.getLogger(ClientRegistrationService.class);

	@Override
	public boolean validateEntity(MetaEntity entity) {
		if (!(entity instanceof Client)) {
			LOGGER.error("Invalid entity for client registration");
			return false;
		}

		Client client = (Client) entity;
		
		String clientRegId = client.getClientRegId();
		String clientName = client.getClientName();
		
		if(clientRegId == null || "".equals(clientRegId) || clientName == null || "".equals(clientName)) {
			String errorCode = ErrorCode.ONE.toString();
			throw new RegistrationException(errorCode);
		}

		if (!this.baseUtilityService.isClientRegIdUnique(clientRegId)) {
			String errorCode = ErrorCode.TWO.toString();
			throw new RegistrationException(errorCode);
		}

		return true;
	}

	@Override
	public void registerEntity(MetaEntity entity) {
		if (this.validateEntity(entity)) {
			Client clientToRegister = (Client) entity;

			LOGGER.info("Registering client: " + clientToRegister.getClientName());
			clientRepository.save(clientToRegister);
		}
	}

}
