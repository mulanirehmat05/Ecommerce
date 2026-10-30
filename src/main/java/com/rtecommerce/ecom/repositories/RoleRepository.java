package com.rtecommerce.ecom.repositories;

import com.rtecommerce.ecom.model.AppRole;
import com.rtecommerce.ecom.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
