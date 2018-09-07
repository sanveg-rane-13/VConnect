package com.hopes.connect.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author SaNNy - Sep 8, 2018
 */

@Entity
@Table(name = "user_info")
public class UserInfo implements AbstractModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_INFO_ID")
	private Long userInfoId;

	@NotNull
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE")
	private Date birthdate;

	@NotNull
	@Email
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "PROFILE_IMAGE_LOC")
	private String profileImageLoc;

	@NotNull
	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "CITY")
	private String city;

	@Column(name = "COUNTRY")
	private String country;

	@OneToOne
	@MapsId
	private User user;

	public UserInfo() {
	}

	public UserInfo(Long userInfoId, String firstName, String middleName, String lastName, Date birthdate, String email,
			String phone, String profileImageLoc, String address, String city, String country, User user) {
		super();
		this.userInfoId = userInfoId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.email = email;
		this.phone = phone;
		this.profileImageLoc = profileImageLoc;
		this.address = address;
		this.city = city;
		this.country = country;
		this.user = user;
	}

	public Long getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(Long userInfoId) {
		this.userInfoId = userInfoId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
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

	public String getProfileImageLoc() {
		return profileImageLoc;
	}

	public void setProfileImageLoc(String profileImageLoc) {
		this.profileImageLoc = profileImageLoc;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
