package net.yazidi.delta.repository;

import net.yazidi.delta.entity.LignesVente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LignesVenteRepository extends JpaRepository<LignesVente,Long> {
}
