package com.motoparts.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motoparts.order.entity.Order;
import com.motoparts.user.entity.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUser(User user);

}