package com.motoparts.inventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motoparts.exception.BusinessException;
import com.motoparts.exception.ResourceNotFoundException;
import com.motoparts.inventory.dto.InventoryRequest;
import com.motoparts.inventory.dto.InventoryResponse;
import com.motoparts.inventory.dto.LowStockResponse;
import com.motoparts.inventory.dto.StockMovementResponse;
import com.motoparts.inventory.entity.Inventory;
import com.motoparts.inventory.entity.StockMovement;
import com.motoparts.inventory.repository.InventoryRepository;
import com.motoparts.inventory.repository.StockMovementRepository;
import com.motoparts.inventory.service.InventoryService;
import com.motoparts.product.entity.Product;
import com.motoparts.product.repository.ProductRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	private final InventoryRepository inventoryRepository;

	private final ProductRepository productRepository;

	private final StockMovementRepository stockMovementRepository;

	public InventoryServiceImpl(InventoryRepository inventoryRepository, ProductRepository productRepository,
			StockMovementRepository stockMovementRepository) {

		this.inventoryRepository = inventoryRepository;
		this.productRepository = productRepository;
		this.stockMovementRepository = stockMovementRepository;
	}

	@Override
	public InventoryResponse createInventory(InventoryRequest request) {

		if (inventoryRepository.findByProductId(request.getProductId()).isPresent()) {

			throw new BusinessException("Inventory already exists for product");
		}

		Product product = productRepository.findById(request.getProductId())
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		Inventory inventory = new Inventory();

		inventory.setProduct(product);
		inventory.setQuantityAvailable(request.getQuantityAvailable());

		inventory.setReservedQuantity(0);

		inventory.setReorderLevel(request.getReorderLevel());

		Inventory savedInventory = inventoryRepository.save(inventory);

		return mapToResponse(savedInventory);
	}

	@Override
	public InventoryResponse getInventoryByProduct(Long productId) {

		Inventory inventory = inventoryRepository.findByProductId(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));

		return mapToResponse(inventory);
	}

	@Override
	public InventoryResponse updateInventory(Long productId, InventoryRequest request) {

		Inventory inventory = inventoryRepository.findByProductId(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));

		inventory.setQuantityAvailable(request.getQuantityAvailable());

		inventory.setReorderLevel(request.getReorderLevel());

		Inventory updatedInventory = inventoryRepository.save(inventory);

		return mapToResponse(updatedInventory);
	}

	private InventoryResponse mapToResponse(Inventory inventory) {

		InventoryResponse response = new InventoryResponse();

		response.setId(inventory.getId());

		response.setProductId(inventory.getProduct().getId());

		response.setProductName(inventory.getProduct().getName());

		response.setQuantityAvailable(inventory.getQuantityAvailable());

		response.setReservedQuantity(inventory.getReservedQuantity());

		response.setReorderLevel(inventory.getReorderLevel());

		response.setUpdatedAt(inventory.getUpdatedAt());

		return response;
	}

	private StockMovementResponse mapToStockMovementResponse(StockMovement movement) {

		StockMovementResponse response = new StockMovementResponse();

		response.setId(movement.getId());

		response.setMovementType(movement.getMovementType().name());

		response.setQuantity(movement.getQuantity());

		response.setPreviousQuantity(movement.getPreviousQuantity());

		response.setNewQuantity(movement.getNewQuantity());

		response.setReason(movement.getReason());

		response.setCreatedAt(movement.getCreatedAt());

		return response;
	}

	@Override
	public List<StockMovementResponse> getStockHistory(Long productId) {

		return stockMovementRepository.findByInventoryProductIdOrderByCreatedAtDesc(productId).stream()
				.map(this::mapToStockMovementResponse).toList();
	}

	private LowStockResponse mapToLowStockResponse(Inventory inventory) {

		LowStockResponse response = new LowStockResponse();

		response.setProductId(inventory.getProduct().getId());

		response.setProductName(inventory.getProduct().getName());

		response.setQuantityAvailable(inventory.getQuantityAvailable());

		return response;
	}

	@Override
	public List<LowStockResponse> getLowStockProducts() {

		return inventoryRepository.findByQuantityAvailableLessThanEqual(5).stream().map(this::mapToLowStockResponse)
				.toList();
	}
}