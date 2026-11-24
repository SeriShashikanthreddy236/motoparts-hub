package com.motoparts.inventory.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.inventory.dto.InventoryRequest;
import com.motoparts.inventory.dto.InventoryResponse;
import com.motoparts.inventory.dto.LowStockResponse;
import com.motoparts.inventory.dto.StockMovementResponse;
import com.motoparts.inventory.service.InventoryService;
import com.motoparts.response.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

	private final InventoryService inventoryService;

	public InventoryController(InventoryService inventoryService) {

		this.inventoryService = inventoryService;
	}

	@PostMapping
	public ApiResponse<InventoryResponse> createInventory(@Valid @RequestBody InventoryRequest request) {

		return new ApiResponse<>(true, "Inventory created successfully", inventoryService.createInventory(request));
	}

	@GetMapping("/product/{productId}")
	public ApiResponse<InventoryResponse> getInventoryByProduct(@PathVariable Long productId) {

		return new ApiResponse<>(true, "Inventory fetched successfully",
				inventoryService.getInventoryByProduct(productId));
	}

	@PutMapping("/product/{productId}")
	public ApiResponse<InventoryResponse> updateInventory(@PathVariable Long productId,
			@Valid @RequestBody InventoryRequest request) {

		return new ApiResponse<>(true, "Inventory updated successfully",
				inventoryService.updateInventory(productId, request));
	}

	@GetMapping("/{productId}/history")
	public ApiResponse<List<StockMovementResponse>> getStockHistory(@PathVariable Long productId) {

		return new ApiResponse<>(true, "Stock history fetched successfully",
				inventoryService.getStockHistory(productId));
	}

	@GetMapping("/low-stock")
	public ApiResponse<List<LowStockResponse>> getLowStockProducts() {

		return new ApiResponse<>(true, "Low stock products fetched successfully",
				inventoryService.getLowStockProducts());
	}
}