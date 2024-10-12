package net.yazidi.delta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import net.yazidi.delta.entity.Categorie;
import net.yazidi.delta.entity.Produit;
import net.yazidi.delta.repository.CategoryRepository;


@Service
public class CategoryService extends AbstractService<Categorie,Long> {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    JpaRepository<Categorie, Long> getRepository() {
        return this.categoryRepository;
    }
}
