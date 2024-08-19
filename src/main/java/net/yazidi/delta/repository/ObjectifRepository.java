package net.yazidi.delta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.yazidi.delta.entity.Objectif;

@Repository
public interface ObjectifRepository extends JpaRepository<Objectif,Long>{

}
