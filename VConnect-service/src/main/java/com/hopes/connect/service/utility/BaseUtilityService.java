package com.hopes.connect.service.utility;

/**
 * @author SaNNy - Sep 8, 2018
 * 
 * desc: basic utility methods to be provided by all implementations
 */
public interface BaseUtilityService {

	/**
	 * 
	 * @param regId
	 * @return boolean - if registration id passed is unique by validating against
	 *         client entries in database
	 */
	public boolean isClientRegIdUnique(String regId);
	
	/**
	 * 
	 * @param serviceName
	 * @return boolean - if service name passed is unique by validating against
	 *         service entries in database 
	 */
	public boolean isServiceUnique(String serviceName);

}
