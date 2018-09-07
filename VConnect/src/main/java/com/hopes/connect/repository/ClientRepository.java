package com.hopes.connect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hopes.connect.model.Client;

/**
 * @author SaNNy - Sep 8, 2018
 */

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
