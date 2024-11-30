package com.contact_manager.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contact_manager.app.model.entities.User;

public interface UserRepo extends JpaRepository<User,Long> {

}
