package com.example.estore.admin.model;

public class Users {

	private String street;
	private String city;
	private String state;
	private String country;
	private Integer pincode;
	
	public Users() {
		super();
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Users [street=" + street + ", city=" + city + ", state=" + state + ", country=" + country + ", pincode="
				+ pincode + "]";
	}

	
	
}
