package com.contact_manager.app.repository;

import com.contact_manager.app.enums.RoleTypes;
import com.contact_manager.app.model.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByRole(RoleTypes role);
}