package com.hopes.connect.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author SaNNy - Sep 8, 2018
 */

enum ClientStatus {
	A, T;
}

@Entity
@Table(name = "client")
public class Client implements MetaEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENT_ID")
	private Long clientId;

	@NotNull
	@Column(name = "CLIENT_NAME", unique = true)
	private String clientName;

	@NotNull
	@Column(name = "CLIENT_REG_ID", unique = true)
	private String clientRegId;

	@NotNull
	@Column(name = "CLIENT_PASSWORD")
	private String clientPassword;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "IS_ACTIVE")
	private ClientStatus isActive;

	@Column(name = "DOMAIN")
	private String domain;

	@NotNull
	@Email
	@Column(name = "EMAIL")
	private String email;

	@Pattern(regexp = "(^$|[0-9]{10})")
	@Column(name = "PHONE")
	private String phone;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<User> users;

	@OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<ClientService> services;

	public Client() {
	}

	public Client(String clientName, String clientRegId, String clientPassword, ClientStatus isActive, String domain,
			String email, String phone) {
		super();
		this.clientName = clientName;
		this.clientRegId = clientRegId;
		this.clientPassword = clientPassword;
		this.isActive = isActive;
		this.domain = domain;
		this.email = email;
		this.phone = phone;
		this.users = new ArrayList<>();
		this.services = new HashSet<>();
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientRegId() {
		return clientRegId;
	}

	public void setClientRegId(String clientRegId) {
		this.clientRegId = clientRegId;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public ClientStatus getIsActive() {
		return isActive;
	}

	public void setIsActive(ClientStatus isActive) {
		this.isActive = isActive;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
