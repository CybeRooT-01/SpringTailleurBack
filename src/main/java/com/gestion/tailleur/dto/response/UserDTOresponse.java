package com.gestion.tailleur.dto.response;

import com.gestion.tailleur.Models.Role;

public record UserDTOresponse(
        int id,
        String nom,
        String email,
        Boolean actif,

        Role role
) {
}
