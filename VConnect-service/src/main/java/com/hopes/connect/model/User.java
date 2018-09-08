package com.hopes.connect.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author SaNNy - Sep 8, 2018
 */

@Entity
@Table(name = "user")
public class User implements MetaEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long userId;

	@NotNull
	@Column(name = "USER_REG_ID", unique = true)
	private String userRegId;

	@NotNull
	@Column(name = "USER_PASSWORD")
	private String userPassword;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "USER_INFO_ID")
	private UserInfo userInfo;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID", nullable = false)
	private Client client;

	public User() {
	}

	public User(Long userId, String userRegId, String userPassword, UserInfo userInfo) {
		super();
		this.userId = userId;
		this.userRegId = userRegId;
		this.userPassword = userPassword;
		this.userInfo = userInfo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserRegId() {
		return userRegId;
	}

	public void setUserRegId(String userRegId) {
		this.userRegId = userRegId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
