package com.motoparts.vehicle.dto;

public class VehicleModelRequest {

	private String name;
	private String code;
	private String vehicleType;
	private Long makeId;

	public VehicleModelRequest() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Long getMakeId() {
		return makeId;
	}

	public void setMakeId(Long makeId) {
		this.makeId = makeId;
	}
}
