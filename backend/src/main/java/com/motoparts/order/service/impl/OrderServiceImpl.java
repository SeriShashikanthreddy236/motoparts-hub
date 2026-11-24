package com.motoparts.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.motoparts.cart.entity.Cart;
import com.motoparts.cart.entity.CartItem;
import com.motoparts.cart.repository.CartItemRepository;
import com.motoparts.cart.repository.CartRepository;
import com.motoparts.exception.BusinessException;
import com.motoparts.exception.ResourceNotFoundException;
import com.motoparts.inventory.entity.Inventory;
import com.motoparts.inventory.entity.StockMovement;
import com.motoparts.inventory.enums.MovementType;
import com.motoparts.inventory.repository.InventoryRepository;
import com.motoparts.inventory.repository.StockMovementRepository;
import com.motoparts.order.dto.OrderItemResponse;
import com.motoparts.order.dto.OrderResponse;
import com.motoparts.order.entity.Order;
import com.motoparts.order.entity.OrderItem;
import com.motoparts.order.enums.OrderStatus;
import com.motoparts.order.repository.OrderItemRepository;
import com.motoparts.order.repository.OrderRepository;
import com.motoparts.order.service.OrderService;
import com.motoparts.seller.entity.SellerProfile;
import com.motoparts.seller.repository.SellerProfileRepository;
import com.motoparts.user.entity.User;
import com.motoparts.user.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final UserRepository userRepository;
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final InventoryRepository inventoryRepository;
	private final StockMovementRepository stockMovementRepository;
	private final SellerProfileRepository sellerProfileRepository;

	public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
			UserRepository userRepository, CartRepository cartRepository, CartItemRepository cartItemRepository,
			InventoryRepository inventoryRepository, StockMovementRepository stockMovementRepository,
			SellerProfileRepository sellerProfileRepository) {

		this.orderRepository = orderRepository;
		this.orderItemRepository = orderItemRepository;
		this.userRepository = userRepository;
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.inventoryRepository = inventoryRepository;
		this.stockMovementRepository = stockMovementRepository;
		this.sellerProfileRepository = sellerProfileRepository;
	}

	@Override
	@Transactional
	public OrderResponse placeOrder(String email) {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

		List<CartItem> cartItems = cartItemRepository.findByCart(cart);

		if (cartItems.isEmpty()) {
			throw new BusinessException("Cart is empty");
		}

		BigDecimal totalAmount = BigDecimal.ZERO;

		for (CartItem cartItem : cartItems) {

			Inventory inventory = inventoryRepository.findByProductId(cartItem.getProduct().getId())
					.orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));

			if (inventory.getQuantityAvailable() < cartItem.getQuantity()) {

				throw new BusinessException("Insufficient stock for " + cartItem.getProduct().getName());
			}

			totalAmount = totalAmount
					.add(cartItem.getProduct().getSellingPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
		}

		Order order = new Order();

		order.setUser(user);
		order.setTotalAmount(totalAmount);

		order = orderRepository.save(order);

		for (CartItem cartItem : cartItems) {

			OrderItem orderItem = new OrderItem();

			orderItem.setOrder(order);

			orderItem.setProduct(cartItem.getProduct());

			orderItem.setQuantity(cartItem.getQuantity());

			orderItem.setUnitPrice(cartItem.getProduct().getSellingPrice());

			orderItem.setTotalPrice(
					cartItem.getProduct().getSellingPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));

			orderItemRepository.save(orderItem);

			Inventory inventory = inventoryRepository.findByProductId(cartItem.getProduct().getId())
					.orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));

			Integer previousQuantity = inventory.getQuantityAvailable();

			Integer quantitySold = cartItem.getQuantity();

			Integer newQuantity = previousQuantity - quantitySold;

			inventory.setQuantityAvailable(newQuantity);

			inventoryRepository.save(inventory);

			StockMovement movement = new StockMovement();

			movement.setInventory(inventory);

			movement.setMovementType(MovementType.OUT);

			movement.setQuantity(quantitySold);

			movement.setPreviousQuantity(previousQuantity);

			movement.setNewQuantity(newQuantity);

			movement.setReason("Order placed");

			stockMovementRepository.save(movement);
		}

		cartItemRepository.deleteAll(cartItems);

		return mapToResponse(order);
	}

	@Override
	public List<OrderResponse> getMyOrders(String email) {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return orderRepository.findByUser(user).stream().map(this::mapToResponse).toList();
	}

	@Override
	public OrderResponse getOrderById(Long orderId, String email) {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found"));

		if (!order.getUser().getId().equals(user.getId())) {

			throw new BusinessException("Unauthorized access");
		}

		return mapToResponse(order);
	}

	private OrderResponse mapToResponse(Order order) {

		List<OrderItemResponse> items = new ArrayList<>();

		List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());

		for (OrderItem item : orderItems) {

			OrderItemResponse response = new OrderItemResponse();

			response.setProductId(item.getProduct().getId());

			response.setProductName(item.getProduct().getName());

			response.setQuantity(item.getQuantity());

			response.setUnitPrice(item.getUnitPrice());

			response.setTotalPrice(item.getTotalPrice());

			items.add(response);
		}

		OrderResponse response = new OrderResponse();

		response.setOrderId(order.getId());

		response.setTotalAmount(order.getTotalAmount());

		response.setStatus(order.getStatus());

		response.setOrderedAt(order.getOrderedAt());

		response.setItems(items);

		return response;
	}

	@Override
	public OrderResponse updateOrderStatus(Long orderId, OrderStatus status) {

		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found"));

		order.setStatus(status);

		Order updated = orderRepository.save(order);

		return mapToResponse(updated);
	}

	@Override
	public List<OrderResponse> getSellerOrders(String email) {

	    User user = userRepository.findByEmail(email)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("User not found"));

	    SellerProfile seller = sellerProfileRepository.findByUser(user)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Seller profile not found"));

	    return orderRepository.findAll()
	            .stream()
	            .filter(order -> orderItemRepository
	                    .findByOrderId(order.getId())
	                    .stream()
	                    .anyMatch(item ->
	                            item.getProduct()
	                                    .getSeller()
	                                    .getId()
	                                    .equals(seller.getId())))
	            .map(this::mapToResponse)
	            .toList();
	}
}