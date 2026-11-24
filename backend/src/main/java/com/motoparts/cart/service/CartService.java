package com.motoparts.cart.service;

import com.motoparts.cart.dto.AddToCartRequest;
import com.motoparts.cart.dto.CartResponse;
import com.motoparts.cart.dto.UpdateCartItemRequest;

public interface CartService {

	CartResponse addToCart(String email, AddToCartRequest request);

	CartResponse getCart(String email);

	CartResponse updateCartItem(Long itemId, UpdateCartItemRequest request, String email);

	void removeCartItem(Long itemId, String email);
}
