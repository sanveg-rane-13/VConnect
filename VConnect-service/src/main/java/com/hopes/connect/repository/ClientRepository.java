package com.hopes.connect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hopes.connect.model.Client;

/**
 * @author SaNNy - Sep 8, 2018
 */

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	/**
	 * 
	 * @param clientRegId
	 * @return Client having the supplied registration id
	 * 
	 */
	public Client findByClientRegId(String clientRegId);
	
	/**
	 * 
	 * @param clientName
	 * @return Client having the supplied name
	 */
	public Client findByClientName(String clientName);
	
	/**
	 * 
	 * @param clientId
	 * @return Client having the supplied ID
	 */
	public Client findByClientId(Long clientId);

}
