package com.gestion.tailleur.dto.response;

import com.gestion.tailleur.enums.TypeCategories;

public record CategoryDTOresponse(
        int id,
        String libelle,

        TypeCategories typeCategories
) {

}
