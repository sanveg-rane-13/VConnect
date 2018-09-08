package com.hopes.connect.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * @author SaNNy - Sep 8, 2018
 * 
 * desc: Many to many mapping entity for business service and client, not to be mistaken for a service implementation
 */

@Entity
@Table(name = "client_service")
public class ClientService implements MetaEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClientServiceId clientServiceId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("clientId")
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("serviceId")
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

	public ClientService(ClientServiceId clientServiceId, Client client, Service service, Date subStartDate,
			Date subEndDate) {
		super();
		this.clientServiceId = clientServiceId;
		this.client = client;
		this.service = service;
		this.subStartDate = subStartDate;
		this.subEndDate = subEndDate;
	}

	public ClientServiceId getClientServiceId() {
		return clientServiceId;
	}

	public void setClientServiceId(ClientServiceId clientServiceId) {
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

@Embeddable
class ClientServiceId implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENT_SERVICE_ID")
	private Long clientServiceId;

	@Column(name = "CLIENT_ID")
	private Long clientId;

	@Column(name = "SERVICE_ID")
	private Long serviceId;

	public ClientServiceId() {
	}

	public ClientServiceId(Long clientServiceId, Long clientId, Long serviceId) {
		super();
		this.clientServiceId = clientServiceId;
		this.clientId = clientId;
		this.serviceId = serviceId;
	}

	public Long getClientServiceId() {
		return clientServiceId;
	}

	public void setClientServiceId(Long clientServiceId) {
		this.clientServiceId = clientServiceId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	// @Embeddable type must override the default equals and hashCode methods based
	// on the two Primary Key identifier values

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;

		ClientServiceId that = (ClientServiceId) obj;
		return Objects.equals(this.clientId, that.clientId) && Objects.equals(this.serviceId, that.serviceId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientId, serviceId);
	}
}
