package com.motoparts.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motoparts.inventory.entity.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

	List<StockMovement> findByInventoryProductIdOrderByCreatedAtDesc(Long productId);
}