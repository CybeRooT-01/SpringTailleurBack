package com.gestion.tailleur.controllers;


import com.gestion.tailleur.Models.Categories;
import com.gestion.tailleur.dto.response.CategoryDTO;
import com.gestion.tailleur.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping("/categorie")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping()
    public Stream<CategoryDTO> listerCategories() {
        return this.categoryService.getAll();
    }
    @PostMapping()
    public ResponseEntity<Categories> ajouterCategorie(@RequestBody Categories categorie) {
        Categories savedCategorie = this.categoryService.creer(categorie);
        if (savedCategorie == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategorie);
    }
    @DeleteMapping()
    public String delete(@RequestBody List<Integer> id){
        for (Integer integer : id) {
            Categories existingCategorie = this.categoryService.getOneById(integer);
            if (existingCategorie == null){
                String message = "La catégorie n'existe pas.";
                int status = HttpStatus.NOT_FOUND.value();
            }
            this.categoryService.delete(id);
        }
        this.categoryService.delete(id);
        int status = HttpStatus.OK.value();
        return "Categorie supprimer avec succes";
    }
    @PutMapping("/{id}")
    public  Categories update(@PathVariable int id, @RequestBody Categories categories){
        Categories existingCategorie = this.categoryService.getOneById(id);
        if (existingCategorie == null){
            String message = "La catégorie n'existe pas.";
            int status = HttpStatus.NOT_FOUND.value();
            return null;
        }
        this.categoryService.update(id, categories);
        String message = "Categorie Modifier avec succes";
        return this.categoryService.getOneById(id);
    }

}
