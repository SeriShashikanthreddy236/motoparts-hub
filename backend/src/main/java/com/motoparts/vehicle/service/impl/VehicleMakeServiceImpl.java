package com.motoparts.vehicle.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motoparts.vehicle.dto.VehicleMakeRequest;
import com.motoparts.vehicle.entity.VehicleMake;
import com.motoparts.vehicle.repository.VehicleMakeRepository;
import com.motoparts.vehicle.service.VehicleMakeService;

@Service
public class VehicleMakeServiceImpl implements VehicleMakeService {

	private final VehicleMakeRepository vehicleMakeRepository;

	public VehicleMakeServiceImpl(VehicleMakeRepository vehicleMakeRepository) {

		this.vehicleMakeRepository = vehicleMakeRepository;
	}

	@Override
	public VehicleMake createMake(VehicleMakeRequest request) {

		if (vehicleMakeRepository.existsByCode(request.getCode())) {

			throw new RuntimeException("Vehicle make code already exists");
		}

		VehicleMake make = new VehicleMake();

		make.setName(request.getName());
		make.setCode(request.getCode());
		make.setLogoUrl(request.getLogoUrl());
		make.setActive(true);

		return vehicleMakeRepository.save(make);
	}

	@Override
	public List<VehicleMake> getAllMakes() {

		return vehicleMakeRepository.findByActiveTrue();
	}

	@Override
	public VehicleMake getMakeById(Long id) {

		return vehicleMakeRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle make not found"));
	}

	@Override
	public VehicleMake updateMake(Long id, VehicleMakeRequest request) {

		VehicleMake make = vehicleMakeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vehicle make not found"));

		make.setName(request.getName());
		make.setCode(request.getCode());
		make.setLogoUrl(request.getLogoUrl());

		return vehicleMakeRepository.save(make);
	}

	@Override
	public VehicleMake updateMakeStatus(Long id, Boolean active) {

		VehicleMake make = vehicleMakeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vehicle make not found"));

		make.setActive(active);

		return vehicleMakeRepository.save(make);
	}
}