package com.motoparts.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motoparts.cart.entity.Cart;
import com.motoparts.user.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long> {

	Optional<Cart> findByUser(User user);
}