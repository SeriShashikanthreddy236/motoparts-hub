package com.motoparts.seller.entity;

import java.time.LocalDateTime;

import com.motoparts.seller.enums.SellerStatus;
import com.motoparts.user.entity.User;

import jakarta.persistence.*;

@Entity
@Table(name = "seller_profiles")
public class SellerProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;

	@Column(nullable = false)
	private String businessName;

	@Column(nullable = false)
	private String businessEmail;

	@Column(nullable = false)
	private String businessPhone;

	private String gstNumber;

	@Column(nullable = false)
	private String businessAddress;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String state;

	@Column(nullable = false)
	private String pincode;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SellerStatus verificationStatus;

	private String rejectionReason;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	// Default Constructor
	public SellerProfile() {
	}

	// Parameterized Constructor
	public SellerProfile(Long id, User user, String businessName, String businessEmail, String businessPhone,
			String gstNumber, String businessAddress, String city, String state, String pincode,
			SellerStatus verificationStatus, String rejectionReason, LocalDateTime createdAt, LocalDateTime updatedAt) {

		this.id = id;
		this.user = user;
		this.businessName = businessName;
		this.businessEmail = businessEmail;
		this.businessPhone = businessPhone;
		this.gstNumber = gstNumber;
		this.businessAddress = businessAddress;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.verificationStatus = verificationStatus;
		this.rejectionReason = rejectionReason;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();

		if (verificationStatus == null) {
			verificationStatus = SellerStatus.PENDING;
		}
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public SellerStatus getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(SellerStatus verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}