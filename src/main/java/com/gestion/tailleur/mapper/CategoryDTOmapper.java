package com.gestion.tailleur.mapper;

import com.gestion.tailleur.Models.Categories;
import com.gestion.tailleur.dto.response.CategoryDTOresponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CategoryDTOmapper implements Function<Categories, CategoryDTOresponse> {
    @Override
    public CategoryDTOresponse apply(Categories categories) {
        return new CategoryDTOresponse(categories.getId(), categories.getLibelle(), categories.getTypeCategories());
    }
}
