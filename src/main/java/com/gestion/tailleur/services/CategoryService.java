package com.gestion.tailleur.services;

import com.gestion.tailleur.Models.Categories;
import com.gestion.tailleur.dto.requests.CategoryDTOrequest;
import com.gestion.tailleur.dto.requests.ListIdsDTO;
import com.gestion.tailleur.dto.response.CategoryDTOresponse;
import com.gestion.tailleur.enums.TypeCategories;
import com.gestion.tailleur.mapper.CategoryDTOmapper;
import com.gestion.tailleur.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDTOmapper categoryDTOmapper;

    public CategoryDTOresponse creer(CategoryDTOrequest categoryDTOrequest) {
        Categories categories = Categories.builder()
                .libelle(categoryDTOrequest.libelle())
                .typeCategories(categoryDTOrequest.typeCategories())
                .build();
        this.categoryRepository.save(categories);
        return this.categoryDTOmapper.apply(categories);
    }

    public Categories getOneById(int id) {
        return this.categoryRepository.findById(id).orElse(null);
    }

    public Stream<CategoryDTOresponse> getAll() {
        return this.categoryRepository.findAll()
                .stream().map(categoryDTOmapper);
    }


    public void delete(ListIdsDTO listIdsDTO) {
        List<Integer> ids = listIdsDTO.id();
        for (Integer id : ids) {
            this.categoryRepository.deleteById(id);
        }
    }

    public CategoryDTOresponse update(int id, CategoryDTOrequest categories) {
        Categories existingCategorie = this.categoryRepository.findById(id).get();
        existingCategorie.setLibelle(categories.libelle());
        existingCategorie.setTypeCategories(categories.typeCategories());
        this.categoryRepository.save(existingCategorie);
        return this.categoryDTOmapper.apply(existingCategorie);
    }

    public Stream<CategoryDTOresponse> getAllCategorieConfection() {
        return this.categoryRepository.findByTypeCategories(TypeCategories.CONFECTION)
                .stream().map(categoryDTOmapper);
    }
    public Stream<CategoryDTOresponse> getAllCategorieVente() {
        return this.categoryRepository.findByTypeCategories(TypeCategories.VENTE)
                .stream().map(categoryDTOmapper);
    }
}
