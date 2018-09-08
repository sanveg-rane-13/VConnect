package com.hopes.connect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hopes.connect.model.Service;

/**
 * @author SaNNy - Sep 8, 2018
 */

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

	/**
	 * 
	 * @param serviceName
	 * @return Service having supplied service name
	 * 
	 */
	public Service findByServiceName(String serviceName);

}
