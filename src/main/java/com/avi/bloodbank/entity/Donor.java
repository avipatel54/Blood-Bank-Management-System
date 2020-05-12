package com.avi.bloodbank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="donor")
public class Donor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="full_name")
	private String name;
	
	@Column(name="city")
	private String city;
	
	@Column(name="country")
	private String country;
	
	@Column(name="contact")
	private String contact;
	
	@Column(name="blood_group")
	private String blood_group;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String pwd;
	
	public Donor() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	public Donor(int id, String name, String city, String country, String contact, String blood_group, String email,
			String pwd) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.country = country;
		this.contact = contact;
		this.blood_group = blood_group;
		this.email = email;
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "Donor [id=" + id + ", name=" + name + ", city=" + city + ", country=" + country + ", contact=" + contact
				+ ", blood_group=" + blood_group + ", email=" + email + ", pwd=" + pwd + "]";
	}

	
	
}
