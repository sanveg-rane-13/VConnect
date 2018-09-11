package com.hopes.connect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hopes.connect.model.ClientService;

/**
 * @author SaNNy - Sep 8, 2018
 */

@Repository
public interface ClientServiceRepository extends JpaRepository<ClientService, Long> {

	/**
	 * 
	 * @param clientId
	 * @param serviceId
	 * @return ClientService
	 */
	public ClientService findByClient_ClientIdAndService_ServiceId(Long clientId, Long serviceId);

}
