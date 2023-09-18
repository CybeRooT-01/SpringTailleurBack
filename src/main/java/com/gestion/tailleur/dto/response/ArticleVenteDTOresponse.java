package com.gestion.tailleur.dto.response;

import com.gestion.tailleur.Models.ArticleConf;
import com.gestion.tailleur.Models.Categories;

import java.util.Collection;
import java.util.Set;

public record ArticleVenteDTOresponse(
        int id,
        String libelle,
        String image,
        String reference,
        int prix_vente,
        int marge,

        int cout_fabrication,
        Categories categories,

        Collection<ArticleConf> articleConf


) {
}
