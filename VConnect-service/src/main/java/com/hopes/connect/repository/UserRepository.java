package com.hopes.connect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hopes.connect.model.User;

/**
 * @author SaNNy - Sep 8, 2018
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * 
	 * 
	 * @param userRegId
	 * @return User
	 */
	public User findByUserRegId(String userRegId);
	
}
