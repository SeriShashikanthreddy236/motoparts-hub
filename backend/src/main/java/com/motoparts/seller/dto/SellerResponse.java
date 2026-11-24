package com.motoparts.seller.dto;

import com.motoparts.seller.enums.SellerStatus;

public class SellerResponse {

    private Long id;

    private String businessName;

    private String businessEmail;

    private String businessPhone;

    private String gstNumber;

    private String businessAddress;

    private String city;

    private String state;

    private String pincode;

    private SellerStatus verificationStatus;

    private String rejectionReason;

    // Default Constructor
    public SellerResponse() {
    }

    // Parameterized Constructor
    public SellerResponse(
            Long id,
            String businessName,
            String businessEmail,
            String businessPhone,
            String gstNumber,
            String businessAddress,
            String city,
            String state,
            String pincode,
            SellerStatus verificationStatus,
            String rejectionReason) {

        this.id = id;
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
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}