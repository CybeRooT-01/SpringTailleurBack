package com.gestion.tailleur.controllers;

import com.gestion.tailleur.entities.Categories;
import com.gestion.tailleur.response.CategorieResponseMessage;
import com.gestion.tailleur.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categorie")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Categories>> listerCategories() {
        List<Categories> categories = this.categoryService.getAll();
        return ResponseEntity.ok(categories);
    }
    @PostMapping()
    public ResponseEntity<CategorieResponseMessage<Categories>> ajouterCategorie(@RequestBody Categories categorie) {
        Categories savedCategorie = this.categoryService.creer(categorie);
        if (savedCategorie == null) {
            String message = "La catégorie existe déjà.";
            int status = HttpStatus.CONFLICT.value();
            CategorieResponseMessage<Categories> response = new CategorieResponseMessage<>(message, null, status);
            return ResponseEntity.status(status).body(response);
        }
        String message = "La catégorie a été ajoutée avec succès.";
        int status = HttpStatus.CREATED.value();
        CategorieResponseMessage<Categories> response = new CategorieResponseMessage<>(message, savedCategorie, status);
        return ResponseEntity.status(status).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CategorieResponseMessage<String>> delete(@PathVariable int id){
        Categories existingCategorie = this.categoryService.getOneById(id);
        if (existingCategorie == null){
            String message = "La catégorie n'existe pas.";
            int status = HttpStatus.NOT_FOUND.value();
            CategorieResponseMessage<String> response = new CategorieResponseMessage<>(message, null, status);
            return ResponseEntity.status(status).body(response);
        }
        this.categoryService.delete(id);
        int status = HttpStatus.OK.value();
        String message = "Categorie supprimer avec succes";
        CategorieResponseMessage<String> response = new CategorieResponseMessage<>(message, null, status);
        return ResponseEntity.status(status).body(response);

    }
    @PutMapping("/{id}")
    public  ResponseEntity<CategorieResponseMessage<Categories>> update(@PathVariable int id, @RequestBody Categories categories){
        Categories existingCategorie = this.categoryService.getOneById(id);
        if (existingCategorie == null){
            String message = "La catégorie n'existe pas.";
            int status = HttpStatus.NOT_FOUND.value();
            CategorieResponseMessage<Categories> response = new CategorieResponseMessage<>(message, null, status);
            return ResponseEntity.status(status).body(response);
        }
        this.categoryService.update(id, categories);
        String message = "Categorie Modifier avec succes";
        CategorieResponseMessage<Categories> response = new CategorieResponseMessage<>(message, categories, HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
