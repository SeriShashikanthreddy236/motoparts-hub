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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/inventory")
@Tag(name = "Inventory", description = "Manage product stock levels and inspect stock movement")
public class InventoryController {

	private final InventoryService inventoryService;

	public InventoryController(InventoryService inventoryService) {

		this.inventoryService = inventoryService;
	}

	@PostMapping
	@Operation(summary = "Create inventory", description = "Creates the initial inventory record and stock level for a product.")
	public ApiResponse<InventoryResponse> createInventory(@Valid @RequestBody InventoryRequest request) {

		return new ApiResponse<>(true, "Inventory created successfully", inventoryService.createInventory(request));
	}

	@GetMapping("/product/{productId}")
	@Operation(summary = "Get product inventory", description = "Retrieves the current stock information for a product.")
	public ApiResponse<InventoryResponse> getInventoryByProduct(@PathVariable Long productId) {

		return new ApiResponse<>(true, "Inventory fetched successfully",
				inventoryService.getInventoryByProduct(productId));
	}

	@PutMapping("/product/{productId}")
	@Operation(summary = "Update product inventory", description = "Adjusts or replaces the stock settings for a product.")
	public ApiResponse<InventoryResponse> updateInventory(@PathVariable Long productId,
			@Valid @RequestBody InventoryRequest request) {

		return new ApiResponse<>(true, "Inventory updated successfully",
				inventoryService.updateInventory(productId, request));
	}

	@GetMapping("/{productId}/history")
	@Operation(summary = "Get stock movement history", description = "Lists stock additions, deductions, and adjustments for a product.")
	public ApiResponse<List<StockMovementResponse>> getStockHistory(@PathVariable Long productId) {

		return new ApiResponse<>(true, "Stock history fetched successfully",
				inventoryService.getStockHistory(productId));
	}

	@GetMapping("/low-stock")
	@Operation(summary = "List low-stock products", description = "Returns products whose available quantity is at or below the configured threshold.")
	public ApiResponse<List<LowStockResponse>> getLowStockProducts() {

		return new ApiResponse<>(true, "Low stock products fetched successfully",
				inventoryService.getLowStockProducts());
	}
}