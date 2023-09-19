package com.gestion.tailleur.dto.requests;

import com.gestion.tailleur.Models.Role;

public record UserDTOrequest(
        String password,
        String nom,
        String email
) {
}
