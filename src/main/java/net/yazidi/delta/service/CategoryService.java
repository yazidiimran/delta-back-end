package net.yazidi.delta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import net.yazidi.delta.entity.Categorie;
import net.yazidi.delta.entity.Produit;
import net.yazidi.delta.repository.CategoryRepository;


@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Categorie> findAll() {
        return categoryRepository.findAll();
    }

    public Categorie findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    public List<Produit> findProductsByCategoryId(Long id) {
        return categoryRepository.findProductsByCategoryId(id);
    }

    
}
