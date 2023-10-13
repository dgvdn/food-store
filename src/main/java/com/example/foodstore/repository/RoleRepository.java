package com.example.foodstore.repository;

import com.example.foodstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    //find role by name
    Role findByName(String name);
}
