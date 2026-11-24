package com.motoparts.seller.dto;

import jakarta.validation.constraints.NotBlank;

public class SellerApplicationRequest {

	@NotBlank(message = "Business name is required")
	private String businessName;

	@NotBlank(message = "Business email is required")
	private String businessEmail;

	@NotBlank(message = "Business phone is required")
	private String businessPhone;

	private String gstNumber;

	@NotBlank(message = "Business address is required")
	private String businessAddress;

	@NotBlank(message = "City is required")
	private String city;

	@NotBlank(message = "State is required")
	private String state;

	@NotBlank(message = "Pincode is required")
	private String pincode;

	// Default Constructor
	public SellerApplicationRequest() {
	}

	// Parameterized Constructor
	public SellerApplicationRequest(String businessName, String businessEmail, String businessPhone, String gstNumber,
			String businessAddress, String city, String state, String pincode) {

		this.businessName = businessName;
		this.businessEmail = businessEmail;
		this.businessPhone = businessPhone;
		this.gstNumber = gstNumber;
		this.businessAddress = businessAddress;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessEmail() {
		return businessEmail;
	}

	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
}