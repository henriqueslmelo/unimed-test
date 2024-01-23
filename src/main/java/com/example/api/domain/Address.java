package com.example.api.domain;

import javax.persistence.*;

@Entity
public class Address {

    	@Id
    	@GeneratedValue(strategy = GenerationType.IDENTITY)
    	private Long id;
    
	@Column(nullable = false)
    	private String street;
    
	@Column(nullable = false)
    	private String city;

	@Column(nullable = false)    
	private String state;
	
	@Column(nullable = false)    
	private String zipCode;

	@Column(nullable = false)    
	private String country;

    	public Long getId() {
       		return id;
    	}

    	public void setId(Long id) {
        	this.id = id;
    	}

    	public String getStreet() {
        	return street;
    	}

    	public void setStreet(String street) {
        	this.street = street;
    	}

    	public String getCity() {
        	return city;
    	}

    	public void setCity(String city) {
        	this.city = city;
    	}

    	public String getState() {
        	return state;
    	}

    	public void setState(String state) {
        	this.state = state;
    	}

    	public String getZipCode() {
        	return zipCode;
    	}

    	public void setZipCode(String zipCode) {
        	this.zipCode = zipCode;
    	}

    	public String getCountry() {
        	return country;
    	}

    	public void setCountry(String country) {
        	this.country = country;
    	}
}
