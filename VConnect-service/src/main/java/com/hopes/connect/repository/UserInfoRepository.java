package com.hopes.connect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hopes.connect.model.UserInfo;

/**
 * @author SaNNy - Sep 8, 2018
 */

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	
	/**
	 * 
	 * @param userId
	 * @return user info of passed user id
	 */
	public UserInfo findByUser_UserId(long userId);
	
}
