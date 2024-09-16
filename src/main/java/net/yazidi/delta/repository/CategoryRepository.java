package net.yazidi.delta.repository;

import net.yazidi.delta.entity.Categorie;
import net.yazidi.delta.entity.Produit;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categorie,Long> {

    @Query(value="select new Produit(p.id,p.codeBarre,p.libelle,p.image,p.unite,p.categorie) from Produit p where p.categorie.id=?1")
    List<Produit> findProductsByCategoryId(Long id);
}
