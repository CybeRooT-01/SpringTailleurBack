package com.gestion.tailleur.mapper;

import com.gestion.tailleur.Models.Categories;
import com.gestion.tailleur.dto.response.CategoryDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CategoryDTOmapper implements Function<Categories, CategoryDTO> {
    @Override
    public CategoryDTO apply(Categories categories) {
        return new CategoryDTO(categories.getId(), categories.getLibelle(), categories.getTypeCategories());
    }
}
