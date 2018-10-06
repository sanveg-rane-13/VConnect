package com.hopes.connect.service.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopes.connect.model.Client;
import com.hopes.connect.model.MetaEntity;
import com.hopes.connect.model.User;
import com.hopes.connect.model.UserInfo;
import com.hopes.connect.model.exception.ErrorCode;
import com.hopes.connect.model.exception.RegistrationException;
import com.hopes.connect.repository.UserInfoRepository;
import com.hopes.connect.repository.UserRepository;

/**
 * Check the user - client registration and updates the user info table
 * 
 * @author SaNNy - Sep 12, 2018
 */

@Service("userRegistrationService")
public class UserRegistrationService implements RegistrationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public boolean validateEntity(MetaEntity entity) {
		if (!(entity instanceof User)) {
			String errorCode = ErrorCode.THREE.toString(); // error code if entity type miss match
			throw new RegistrationException(errorCode);
		}

		User user = (User) entity;

		String userRegId = user.getUserRegId();
		Client client = user.getClient();

		if (userRegId == null || "".equals(userRegId)) {
			String errorCode = ErrorCode.ONE.toString(); // error code for null value
			throw new RegistrationException(errorCode);
		}

		if (client == null) {
			String errorCode = ErrorCode.ONE.toString(); // error code for null value
			throw new RegistrationException(errorCode);
		}

		return true;
	}

	/**
	 * Checks if user requested to register is already registered from client
	 * 
	 * @param user
	 */
	private User getValidatedAndUpdatedUser(User user) {
		String userRegId = user.getUserRegId();

		User clientRegisteredUser = userRepository.findByUserRegId(userRegId);

		// check if user registered by client
		if (clientRegisteredUser == null) {
			String errorCode = ErrorCode.SEVEN.toString(); // error code for null value
			throw new RegistrationException(errorCode);
		}

		// check if correct registration i
		if (!clientRegisteredUser.getUserRegId().equals(userRegId)) {
			String errorCode = ErrorCode.SEVEN.toString(); // error code for null value
			throw new RegistrationException(errorCode);
		}

		// check if client is present
		Client userClient = clientRegisteredUser.getClient();
		if (userClient == null) {
			String errorCode = ErrorCode.SEVEN.toString(); // error code for null value
			throw new RegistrationException(errorCode);
		}

		return clientRegisteredUser;

	}

	@Override
	public void registerEntity(MetaEntity entity) {
		if (this.validateEntity(entity)) {
			User user = (User) entity;

			User userToRegister = this.getValidatedAndUpdatedUser(user);

			// Save user info
			UserInfo userInfo = userToRegister.getUserInfo();
			if (userInfo != null) {
				userInfoRepository.save(userInfo);
			}

			userRepository.save(userToRegister);
		}
	}

}
