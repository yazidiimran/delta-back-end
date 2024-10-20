package net.yazidi.delta.repository;

import net.yazidi.delta.entity.LignesBonLivraison;
import net.yazidi.delta.entity.LignesFacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LignesFactureRepository extends JpaRepository<LignesFacture,Long> {
}
