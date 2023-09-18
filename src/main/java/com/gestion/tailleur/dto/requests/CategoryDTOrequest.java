package com.gestion.tailleur.dto.requests;

import com.gestion.tailleur.enums.TypeCategories;

public record CategoryDTOrequest(
        String libelle,
        TypeCategories typeCategories
) {
}
