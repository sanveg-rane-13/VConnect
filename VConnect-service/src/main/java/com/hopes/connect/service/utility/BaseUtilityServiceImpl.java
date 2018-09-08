package com.hopes.connect.service.utility;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopes.connect.repository.ClientRepository;
import com.hopes.connect.service.register.ClientRegistrationService;

/**
 * @author SaNNy - Sep 8, 2018
 */

@Service("baseUtilityService")
public class BaseUtilityServiceImpl implements BaseUtilityService {

	/**
	 * NOTE: Avoid injecting other services in BaseUtilityService. The service must
	 * provide all basic required functionalities and checks as required by other services
	 */

	@Autowired
	private ClientRepository clientRepository;

	private static Logger LOGGER = Logger.getLogger(ClientRegistrationService.class);

	@Override
	public boolean isClientRegIdUnique(String regId) {
		if (regId == null || "".equals(regId)) {
			LOGGER.error("Null RegID received");
			return false;
		}
		return (clientRepository.findByClientRegId(regId) != null) ? false : true;
	}

}
