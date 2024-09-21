package net.yazidi.delta.repository;

import net.yazidi.delta.entity.LignesBonCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LignesBonCommandeRepository extends JpaRepository<LignesBonCommande,Long> {
}
