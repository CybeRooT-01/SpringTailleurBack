package com.gestion.tailleur.dto.response;

import com.gestion.tailleur.enums.TypeCategories;

public record CategoryDTO(
        int id,
        String libelle,

        TypeCategories typeCategories
) {

}
