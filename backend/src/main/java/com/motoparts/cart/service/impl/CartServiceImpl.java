package com.motoparts.cart.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.motoparts.cart.dto.AddToCartRequest;
import com.motoparts.cart.dto.CartItemResponse;
import com.motoparts.cart.dto.CartResponse;
import com.motoparts.cart.dto.UpdateCartItemRequest;
import com.motoparts.cart.entity.Cart;
import com.motoparts.cart.entity.CartItem;
import com.motoparts.cart.repository.CartItemRepository;
import com.motoparts.cart.repository.CartRepository;
import com.motoparts.cart.service.CartService;
import com.motoparts.exception.BusinessException;
import com.motoparts.exception.ResourceNotFoundException;
import com.motoparts.inventory.entity.Inventory;
import com.motoparts.inventory.repository.InventoryRepository;
import com.motoparts.product.entity.Product;
import com.motoparts.product.repository.ProductRepository;
import com.motoparts.user.entity.User;
import com.motoparts.user.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final InventoryRepository inventoryRepository;

	public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository,
			UserRepository userRepository, ProductRepository productRepository,
			InventoryRepository inventoryRepository) {

		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.inventoryRepository = inventoryRepository;
	}

	@Override
	public CartResponse addToCart(String email, AddToCartRequest request) {

		User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
			Cart newCart = new Cart();
			newCart.setUser(user);
			return cartRepository.save(newCart);
		});

		Product product = productRepository.findById(request.getProductId())
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		Inventory inventory = inventoryRepository.findByProductId(product.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));

		if (request.getQuantity() > inventory.getQuantityAvailable()) {

			throw new BusinessException("Insufficient stock available");
		}

		CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product).orElse(null);

		if (cartItem == null) {

			cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setProduct(product);
			cartItem.setQuantity(request.getQuantity());

		} else {

			cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
		}

		cartItemRepository.save(cartItem);

		return buildCartResponse(cart);
	}

	@Override
	public CartResponse getCart(String email) {

		User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

		return buildCartResponse(cart);
	}

	@Override
	public CartResponse updateCartItem(Long itemId, UpdateCartItemRequest request, String email) {

		CartItem item = cartItemRepository.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

		Inventory inventory = inventoryRepository.findByProductId(item.getProduct().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));

		if (request.getQuantity() > inventory.getQuantityAvailable()) {

			throw new BusinessException("Insufficient stock available");
		}

		item.setQuantity(request.getQuantity());

		cartItemRepository.save(item);

		return buildCartResponse(item.getCart());
	}

	@Override
	public void removeCartItem(Long itemId, String email) {

		CartItem item = cartItemRepository.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

		cartItemRepository.delete(item);
	}

	private CartResponse buildCartResponse(Cart cart) {

		List<CartItem> cartItems = cartItemRepository.findByCart(cart);

		List<CartItemResponse> items = new ArrayList<>();

		BigDecimal grandTotal = BigDecimal.ZERO;

		Integer totalItems = 0;

		for (CartItem item : cartItems) {

			CartItemResponse response = new CartItemResponse();

			response.setCartItemId(item.getId());

			response.setProductId(item.getProduct().getId());

			response.setProductName(item.getProduct().getName());

			response.setSku(item.getProduct().getSku());

			response.setQuantity(item.getQuantity());

			BigDecimal unitPrice = item.getProduct().getSellingPrice();

			BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(item.getQuantity()));

			response.setUnitPrice(unitPrice);

			response.setTotalPrice(totalPrice);

			items.add(response);

			grandTotal = grandTotal.add(totalPrice);

			totalItems += item.getQuantity();
		}

		CartResponse cartResponse = new CartResponse();

		cartResponse.setCartId(cart.getId());
		cartResponse.setUserId(cart.getUser().getId());
		cartResponse.setItems(items);
		cartResponse.setTotalItems(totalItems);
		cartResponse.setGrandTotal(grandTotal);

		return cartResponse;
	}
}