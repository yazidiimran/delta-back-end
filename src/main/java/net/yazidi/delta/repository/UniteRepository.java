package net.yazidi.delta.repository;

import net.yazidi.delta.entity.Unite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniteRepository extends JpaRepository<Unite,Long> {
}
