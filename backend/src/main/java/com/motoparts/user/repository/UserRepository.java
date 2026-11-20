package com.motoparts.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motoparts.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);
}