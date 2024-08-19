package net.yazidi.delta.security.repository;

import net.yazidi.delta.security.models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRole(String role);
}
