package com.hopes.connect.service.register;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hopes.connect.model.MetaEntity;
import com.hopes.connect.model.Service;
import com.hopes.connect.model.exception.ErrorCode;
import com.hopes.connect.model.exception.RegistrationException;
import com.hopes.connect.repository.ServiceRepository;
import com.hopes.connect.service.utility.BaseUtilityService;

/**
 * @author SaNNy - Sep 8, 2018
 */

@org.springframework.stereotype.Service("serviceRegistrationService")
public class ServiceRegistrationService implements RegistrationService {

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private BaseUtilityService baseUtilityService;

	private static Logger LOGGER = Logger.getLogger(ServiceRegistrationService.class);

	@Override
	public boolean validateEntity(MetaEntity entity) {
		if (!(entity instanceof Service)) {
			String errorCode = ErrorCode.THREE.toString(); // error code if entity type miss match
			throw new RegistrationException(errorCode);
		}

		Service service = (Service) entity;
		String serviceName = service.getServiceName();

		if (serviceName == null || "".equals(serviceName)) {
			String errorCode = ErrorCode.ONE.toString(); // error code for null value
			throw new RegistrationException(errorCode);
		}

		if (!this.baseUtilityService.isServiceUnique(serviceName)) {
			String errorCode = ErrorCode.TWO.toString(); // error code for duplicate value
			throw new RegistrationException(errorCode);
		}

		return true;
	}

	@Override
	public void registerEntity(MetaEntity entity) {
		if (this.validateEntity(entity)) {
			Service serviceToRegister = (Service) entity;

			serviceToRegister.setStartDate(new Date());
			LOGGER.info("Registering new Service: " + serviceToRegister.getServiceName());
			serviceRepository.save(serviceToRegister);
		}

	}

}
