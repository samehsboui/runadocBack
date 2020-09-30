package com.app.runadoc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.runadoc.enumeration.RoleName;
import com.app.runadoc.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	public Optional<Role> findByName(RoleName roleName);
}