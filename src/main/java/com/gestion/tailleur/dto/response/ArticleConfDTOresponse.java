package com.gestion.tailleur.dto.response;

import com.gestion.tailleur.Models.Categories;
import com.gestion.tailleur.Models.Fournisseur;

import java.util.Set;

public record ArticleConfDTOresponse(
        int id,
        String libelle,
        int prix,
        double stock,
        String reference,
        String image,
        Categories categories,
        Set<Fournisseur> fournisseurs
) {
}
