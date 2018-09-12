package com.hopes.connect.service.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopes.connect.model.MetaEntity;
import com.hopes.connect.model.User;
import com.hopes.connect.model.exception.ErrorCode;
import com.hopes.connect.model.exception.RegistrationException;
import com.hopes.connect.repository.UserRepository;
import com.hopes.connect.service.utility.BaseUtilityService;

/**
 * @author SaNNy - Sep 12, 2018
 */

@Service("userRegistrationService")
public class UserRegistrationService implements RegistrationService {
	
	@Autowired
	private UserRepository userRepository;

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

		if (!baseUtilityService.isUserRegIdUnique(userRegId)) {
			String errorCode = ErrorCode.TWO.toString(); // error code for duplicate value
			throw new RegistrationException(errorCode);
		}

		return true;
	}
	
	private void validateRegistration(User user) {
		
	}

	@Override
	public void registerEntity(MetaEntity entity) {
		if(this.validateEntity(entity)) {
			User userToRegister = (User) entity;
			
			this.validateRegistration(userToRegister);
			
			this.userRepository.save(userToRegister);
		}
	}

}
