package com.hopes.connect.service.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopes.connect.model.Client;
import com.hopes.connect.model.MetaEntity;
import com.hopes.connect.model.User;
import com.hopes.connect.model.exception.ErrorCode;
import com.hopes.connect.model.exception.RegistrationException;
import com.hopes.connect.repository.ClientRepository;
import com.hopes.connect.repository.UserRepository;
import com.hopes.connect.service.utility.BaseUtilityService;

/**
 * Register a user to a client. This entry happens from client side when a user
 * registers with the client. After client registers the user with his unique
 * user_reg_id, the user creates his profile using the reg_id.
 * 
 * - Client enters client_reg_id and a unique user_reg_id. - The entry happens
 * in user table after client is validated properly. - The user registers with
 * the user_reg_id provided by the client and creates profile.
 * 
 * @author SaNNy - Sep 13, 2018
 */

@Service("clientUserRegistrationService")
public class ClientUserRegistrationService implements RegistrationService {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private BaseUtilityService baseUtilityService;

	@Override
	public boolean validateEntity(MetaEntity entity) {
		if (!(entity instanceof User)) {
			String errorCode = ErrorCode.THREE.toString(); // error code if entity type miss match
			throw new RegistrationException(errorCode);
		}

		User user = (User) entity;

		String userRegId = user.getUserRegId();

		if (userRegId == null || "".equals(userRegId)) {
			String errorCode = ErrorCode.ONE.toString(); // error code for null value
			throw new RegistrationException(errorCode);
		}

		Client client = user.getClient();

		if (client == null) {
			String errorCode = ErrorCode.ONE.toString(); // error code for null value
			throw new RegistrationException(errorCode);
		}

		String clientRegId = client.getClientRegId();
		Long clientId = client.getClientId();

		if (clientRegId == null || "".equals(clientRegId) || clientId == null) {
			String errorCode = ErrorCode.ONE.toString(); // error code for null value
			throw new RegistrationException(errorCode);
		}

		if (!baseUtilityService.isUserRegIdUnique(userRegId)) {
			String errorCode = ErrorCode.TWO.toString(); // error code for duplicate value
			throw new RegistrationException(errorCode);
		}

		return true;
	}

	/**
	 * Check if requested client exists and set to the user
	 * 
	 * @param user
	 * @param client
	 */
	private void validateAndUpdateUserClient(User user, Client client) {
		String clientRegId = client.getClientRegId();
		Long clientId = client.getClientId();

		Client registeredClient = clientRepository.findByClientIdAndClientRegId(clientId, clientRegId);

		if (registeredClient == null) {
			String errorCode = ErrorCode.SIX.toString();
			throw new RegistrationException(errorCode);
		}

		user.setClient(registeredClient);
	}

	@Override
	public void registerEntity(MetaEntity entity) {
		if (this.validateEntity(entity)) {
			User user = (User) entity;
			Client client = user.getClient();

			this.validateAndUpdateUserClient(user, client);
			userRepository.save(user);
		}

	}

}
