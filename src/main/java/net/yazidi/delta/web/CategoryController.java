package net.yazidi.delta.web;

import net.yazidi.delta.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import net.yazidi.delta.entity.Categorie;
import net.yazidi.delta.entity.Produit;
import net.yazidi.delta.service.CategoryService;

@Controller
@RequestMapping("/api/categories")
public class CategoryController extends AbstractController<Categorie,Long>{

    @Autowired
    private CategoryService categoryService;

    @Override
    public IService getService() {
        return this.categoryService;
    }
}
