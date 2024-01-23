package com.example.api.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
    	@NotEmpty
	private String name;

	@Column(nullable = false)
	@NotEmpty
	@Email
	private String email;

	@Column(nullable = false)
   	@NotEmpty
    	private String gender;

    	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    	@JoinColumn(name = "customer_id", nullable = false)
    	private List<Address> addresses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

    	public List<Address> getAddresses() {
        	return addresses;
    	}

    	public void setAddresses(List<Address> addresses) {
        	this.addresses = addresses;
    	}

}
