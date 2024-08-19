package net.yazidi.delta.repository;

import net.yazidi.delta.entity.LignesAchat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LignesAchatRepository extends JpaRepository<LignesAchat,Long> {
}
