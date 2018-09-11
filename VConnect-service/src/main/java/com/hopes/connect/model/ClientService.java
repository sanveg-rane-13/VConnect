package com.hopes.connect.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Many to many mapping entity for business service and client, not to be
 * mistaken for a service implementation
 * 
 * @author SaNNy - Sep 8, 2018
 * 
 */

@Entity
@Table(name = "client_service")
public class ClientService implements MetaEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENT_SERVICE_ID")
	private Long clientServiceId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_ID")
	private Service service;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "SUB_START_DATE")
	private Date subStartDate;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "SUB_END_DATE")
	private Date subEndDate;

	public ClientService() {
	}

	public ClientService(Client client, Service service, Date subStartDate, Date subEndDate) {
		super();
		this.client = client;
		this.service = service;
		this.subStartDate = subStartDate;
		this.subEndDate = subEndDate;
	}

	public Long getClientServiceId() {
		return clientServiceId;
	}

	public void setClientServiceId(Long clientServiceId) {
		this.clientServiceId = clientServiceId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Date getSubStartDate() {
		return subStartDate;
	}

	public void setSubStartDate(Date subStartDate) {
		this.subStartDate = subStartDate;
	}

	public Date getSubEndDate() {
		return subEndDate;
	}

	public void setSubEndDate(Date subEndDate) {
		this.subEndDate = subEndDate;
	}

}