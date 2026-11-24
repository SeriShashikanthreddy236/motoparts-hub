package com.motoparts.seller.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motoparts.seller.entity.SellerProfile;
import com.motoparts.user.entity.User;

public interface SellerProfileRepository extends JpaRepository<SellerProfile, Long> {

	boolean existsByUser(User user);

	Optional<SellerProfile> findByUser(User user);

	List<SellerProfile> findAllByOrderByCreatedAtDesc();

}