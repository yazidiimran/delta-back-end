package net.yazidi.delta.repository;

import net.yazidi.delta.entity.Parametre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre,Long> {
}
