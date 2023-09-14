package com.gestion.tailleur.services;

import com.gestion.tailleur.Models.Categories;
import com.gestion.tailleur.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public Categories  creer(Categories categorie) {
        Categories existingCategorie = this.categoryRepository.findByLibelle(categorie.getLibelle()).stream().findFirst().orElse(null);
        if (existingCategorie == null) {
            this.categoryRepository.save(categorie);
            return categorie;
        }
        return null;
    }

    public Categories getOneById(int id){
        return this.categoryRepository.findById(id).orElse(null);
    }

    public List<Categories> getAll() {
        return this.categoryRepository.findAll();
    }

    public void delete(List<Integer> id) {
        for (Integer integer : id) {
            this.categoryRepository.deleteById(integer);
        }
    }

    public void update(int id, Categories categories) {
        Categories existingCategorie = this.categoryRepository.findById(id).orElse(null);
        if (existingCategorie != null) {
            existingCategorie.setLibelle(categories.getLibelle());
            existingCategorie.setTypeCategories(categories.getTypeCategories());
            this.categoryRepository.save(existingCategorie);
        }
    }
}
