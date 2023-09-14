package com.gestion.tailleur.services;

import com.gestion.tailleur.Models.Categories;
import com.gestion.tailleur.dto.response.CategoryDTO;
import com.gestion.tailleur.mapper.CategoryDTOmapper;
import com.gestion.tailleur.projections.CategorieProjection;
import com.gestion.tailleur.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDTOmapper categoryDTOmapper;

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

    public Stream<CategoryDTO> getAll() {
        return this.categoryRepository.findAll()
                .stream().map(categoryDTOmapper);
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
