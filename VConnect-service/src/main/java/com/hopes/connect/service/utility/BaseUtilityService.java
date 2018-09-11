package com.hopes.connect.service.utility;

/**
 * 
 * Basic utility methods to be provided by all implementations
 * 
 * @author SaNNy - Sep 8, 2018
 * 
 */
public interface BaseUtilityService {

	/**
	 * Checks if registration id passed is unique by validating against client
	 * entries in database
	 * 
	 * @param regId
	 * @return boolean
	 */
	public boolean isClientRegIdUnique(String regId);

	/**
	 * Checks if service name passed is unique by validating against service entries
	 * in database
	 * 
	 * @param serviceName
	 * @return boolean
	 */
	public boolean isServiceUnique(String serviceName);

	/**
	 * Checks if Client is already registered to the service by validating against
	 * clientService entries in database
	 * 
	 * @param clientId
	 * @param serviceId
	 * @return boolean
	 */
	public boolean isClientServiceUnique(Long clientId, Long serviceId);

}
