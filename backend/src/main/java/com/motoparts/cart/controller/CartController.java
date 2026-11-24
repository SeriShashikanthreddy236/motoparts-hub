package com.motoparts.cart.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.cart.dto.AddToCartRequest;
import com.motoparts.cart.dto.CartResponse;
import com.motoparts.cart.dto.UpdateCartItemRequest;
import com.motoparts.cart.service.CartService;
import com.motoparts.response.ApiResponse;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/cart")
@Tag(name = "Shopping Cart", description = "Manage products and quantities in the authenticated customer's cart")
public class CartController {

	private final CartService cartService;

	public CartController(CartService cartService) {

		this.cartService = cartService;
	}

	@PostMapping("/items")
	@Operation(summary = "Add an item to the cart", description = "Adds a product and quantity to the authenticated customer's shopping cart.")
	public ApiResponse<CartResponse> addToCart(@Valid @RequestBody AddToCartRequest request, Authentication authentication) {

		return new ApiResponse<>(true, "Item added to cart successfully",
				cartService.addToCart(authentication.getName(), request));
	}

	@GetMapping
	@Operation(summary = "Get the shopping cart", description = "Retrieves the current cart, its items, quantities, and totals.")
	public ApiResponse<CartResponse> getCart(Authentication authentication) {

		return new ApiResponse<>(true, "Cart retrieved successfully", cartService.getCart(authentication.getName()));
	}

	@PutMapping("/items/{itemId}")
	@Operation(summary = "Update a cart item", description = "Changes the quantity or selection of an existing cart item.")
	public ApiResponse<CartResponse> updateCartItem(@PathVariable Long itemId,
			@Valid @RequestBody UpdateCartItemRequest request, Authentication authentication) {

		return new ApiResponse<>(true, "Cart item updated successfully",
				cartService.updateCartItem(itemId, request, authentication.getName()));
	}

	@DeleteMapping("/items/{itemId}")
	@Operation(summary = "Remove a cart item", description = "Deletes a specific item from the authenticated customer's cart.")
	public ApiResponse<String> removeCartItem(@PathVariable Long itemId, Authentication authentication) {

		cartService.removeCartItem(itemId, authentication.getName());

		return new ApiResponse<>(true, "Cart item removed successfully", null);
	}
}