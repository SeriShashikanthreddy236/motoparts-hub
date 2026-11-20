package com.motoparts.role.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motoparts.role.entity.Role;
import com.motoparts.role.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RoleName name);
}