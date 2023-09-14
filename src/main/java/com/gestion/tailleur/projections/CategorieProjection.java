package com.gestion.tailleur.projections;

import com.gestion.tailleur.Models.Categories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "Categorie", types = {Categories.class})
public interface CategorieProjection {
    int getId();
    String getLibelle();
    @Value("#{target.getTypeCategories().toString()}")
    String getTypeCategories();
}
