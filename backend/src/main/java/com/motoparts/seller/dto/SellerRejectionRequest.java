package com.motoparts.seller.dto;

public class SellerRejectionRequest {

	private String reason;

	public SellerRejectionRequest() {
	}

	public SellerRejectionRequest(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}