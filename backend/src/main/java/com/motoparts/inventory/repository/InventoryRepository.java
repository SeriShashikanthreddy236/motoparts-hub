package com.motoparts.inventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motoparts.inventory.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Optional<Inventory> findByProductId(Long productId);

	List<Inventory> findByQuantityAvailableLessThanEqual(Integer quantity);
}