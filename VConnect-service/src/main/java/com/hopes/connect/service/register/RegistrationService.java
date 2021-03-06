package com.hopes.connect.service.register;

import org.apache.log4j.Logger;

import com.hopes.connect.model.MetaEntity;

/**
 * @author SaNNy - Sep 8, 2018
 */
public interface RegistrationService {

	static Logger LOGGER = Logger.getLogger(RegistrationService.class); 
	
	/**
	 * 
	 * @param entity
	 * @return boolean
	 * 
	 *         Validate entity before inserting into database. All necessary
	 *         validations on remote object must be applied here.
	 * 
	 */
	public boolean validateEntity(MetaEntity entity);

	/**
	 * 
	 * @param entity
	 * 
	 *            Insert the entity into database. Validate the entry before
	 *            registering into database.
	 * 
	 */
	public void registerEntity(MetaEntity entity);
}
