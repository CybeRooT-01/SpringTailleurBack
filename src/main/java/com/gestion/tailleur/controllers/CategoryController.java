package com.gestion.tailleur.controllers;


import com.gestion.tailleur.Models.Categories;
import com.gestion.tailleur.dto.requests.CategoryDTOrequest;
import com.gestion.tailleur.dto.requests.ListIdsDTO;
import com.gestion.tailleur.dto.response.CategoryDTOresponse;
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
    public Stream<CategoryDTOresponse> listerCategories() {
        return this.categoryService.getAll();
    }
    @PostMapping()
    public CategoryDTOresponse ajouterCategorie(@RequestBody CategoryDTOrequest categorie) {
        return this.categoryService.creer(categorie);
    }
    @GetMapping("/confection")
    public Stream<CategoryDTOresponse> listerCategoriesConfection() {
        return this.categoryService.getAllCategorieConfection();
    }
@GetMapping("/vente")
    public Stream<CategoryDTOresponse> listerCategoriesVente() {
        return this.categoryService.getAllCategorieVente();
    }
    @DeleteMapping()
    public String delete(@RequestBody ListIdsDTO listIdsDTO){
        List<Integer> ids = listIdsDTO.id();

        for (Integer id : ids) {
            Categories existingCategorie = this.categoryService.getOneById(id);
            if (existingCategorie == null) {
                String message = "La catégorie n'existe pas.";
                int status = HttpStatus.NOT_FOUND.value();
            }
            this.categoryService.delete(listIdsDTO);
        }

        int status = HttpStatus.OK.value();
        return "Categorie supprimée avec succès";
    }

    @PutMapping("/{id}")
    public  CategoryDTOresponse update(@PathVariable int id, @RequestBody CategoryDTOrequest categories) {
        Categories existingCategorie = this.categoryService.getOneById(id);
        if (existingCategorie == null) {
            String message = "La catégorie n'existe pas.";
            int status = HttpStatus.NOT_FOUND.value();
            return null;
        }
        return this.categoryService.update(id, categories);
    }
}
