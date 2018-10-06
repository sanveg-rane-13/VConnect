package com.hopes.connect.service.utility;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hopes.connect.repository.ClientRepository;
import com.hopes.connect.repository.ClientServiceRepository;
import com.hopes.connect.repository.ServiceRepository;
import com.hopes.connect.repository.UserRepository;
import com.hopes.connect.service.register.ClientRegistrationService;

/**
 * @author SaNNy - Sep 8, 2018
 */

@Service("baseUtilityService")
public class BaseUtilityServiceImpl implements BaseUtilityService {

	/**
	 * NOTE: Avoid injecting other services in BaseUtilityService. The service must
	 * provide all basic required functionalities and checks as required by other
	 * services.
	 */

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClientServiceRepository clientServiceRepository;

	private static Logger LOGGER = Logger.getLogger(ClientRegistrationService.class);

	@Override
	public boolean isClientRegIdUnique(String regId) {
		if (regId == null || "".equals(regId)) {
			LOGGER.error("Registration ID null while checking unique");
			return false;
		}
		return (clientRepository.findByClientRegId(regId) != null) ? false : true;
	}

	@Override
	public boolean isServiceUnique(String serviceName) {
		if (serviceName == null || "".equals(serviceName)) {
			LOGGER.error("Service Name null while checking unique");
			return false;
		}
		return (serviceRepository.findByServiceName(serviceName) != null) ? false : true;
	}

	@Override
	public boolean isClientServiceUnique(Long clientId, Long serviceId) {
		if (clientId == null || serviceId == null) {
			LOGGER.error("Client id and Service id cannot be null while registering client-service");
			return false;
		}
		return (clientServiceRepository.findByClient_ClientIdAndService_ServiceId(clientId, serviceId) != null) ? false
				: true;
	}

	@Override
	public boolean isUserRegIdUnique(String regId) {
		if (regId == null || "".equals(regId)) {
			LOGGER.error("User registration id null while checking unique");
			return false;
		}
		return (userRepository.findByUserRegId(regId) != null) ? false : true;

	}
	
	@Override
	public void saveUserProfileImage(MultipartFile profileImage, String userRegId) {
		
	}
	
	

}
