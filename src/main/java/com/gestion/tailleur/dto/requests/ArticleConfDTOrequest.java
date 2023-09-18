package com.gestion.tailleur.dto.requests;

import com.gestion.tailleur.Models.Categories;
import com.gestion.tailleur.Models.Fournisseur;

import java.util.Set;

public record ArticleConfDTOrequest(
        String libelle,
        int prix,
        int stock,
        String reference,
        String image,
        Categories categories,
        Set<Fournisseur> fournisseurs
) {
}
