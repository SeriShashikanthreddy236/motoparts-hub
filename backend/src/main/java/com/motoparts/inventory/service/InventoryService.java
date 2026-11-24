package com.motoparts.inventory.service;

import java.util.List;

import com.motoparts.inventory.dto.InventoryRequest;
import com.motoparts.inventory.dto.InventoryResponse;
import com.motoparts.inventory.dto.LowStockResponse;
import com.motoparts.inventory.dto.StockMovementResponse;

public interface InventoryService {

	InventoryResponse createInventory(InventoryRequest request);

	InventoryResponse getInventoryByProduct(Long productId);

	InventoryResponse updateInventory(Long productId, InventoryRequest request);

	List<StockMovementResponse> getStockHistory(Long productId);
	
	List<LowStockResponse> getLowStockProducts();
}